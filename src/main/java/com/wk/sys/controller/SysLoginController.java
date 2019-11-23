package com.wk.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.wk.sys.constast.SysConstast;
import com.wk.sys.pojo.SysLogLogin;
import com.wk.sys.pojo.SysUser;
import com.wk.sys.service.SysLogLoginService;
import com.wk.sys.service.SysUserService;
import com.wk.sys.utils.WebUtils;
import com.wk.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 用户登录控制器
 */
@Controller
@RequestMapping("login")
public class SysLoginController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysLogLoginService sysLogLoginService;

	//跳转到登录页面
	@RequestMapping("toLogin")
	public String toLogin(){
		return "system/main/login";
	}

	@RequestMapping("login")
	public String login(SysUserVo sysUserVo, Model model){
		String code = WebUtils.getHttpSession().getAttribute("code").toString();
		if(sysUserVo.getCode().equals(code)){
			SysUser sysUser = sysUserService.login(sysUserVo);
			if (sysUser != null) {
				WebUtils.getHttpSession().setAttribute("user",sysUser);

				//记录登录日志,向sys_login_log表中插入数据
				SysLogLogin logLogin = new SysLogLogin();
				logLogin.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
				logLogin.setLoginname(sysUser.getRealname()+"-"+sysUser.getLoginname());
				logLogin.setLogintime(new Date());
				sysLogLoginService.addLog(logLogin);
				return "system/main/index";
			}else {
				//登录失败提示信息
				model.addAttribute("msg", SysConstast.USER_LOGIN_ERROR_MSG);
				return "system/main/login";
			}
		}else {
			//验证码错误提示信息
			model.addAttribute("msg", SysConstast.CAPTCHA_ENTER_ERROR_MSG);
			return "system/main/login";
		}

	}

	/**
	 * 得到登录验证码
	 */
	@RequestMapping("getCode")
	public void getCode(HttpServletResponse response, HttpSession session){
		//定义图形验证码
		ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(116, 36, 4, 4);
		//在session中设置验证码的内容
		session.setAttribute("code",captcha.getCode());
		try {
			//得到输出流
			ServletOutputStream stream = response.getOutputStream();
			//写出图片
			ImageIO.write(captcha.getImage(),"jpeg",stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
