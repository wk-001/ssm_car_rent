package com.wk.sys.controller;

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
	}

}
