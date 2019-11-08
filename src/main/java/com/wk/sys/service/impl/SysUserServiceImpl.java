package com.wk.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.sys.dao.SysUserMapper;
import com.wk.sys.pojo.SysUser;
import com.wk.sys.service.SysUserService;
import com.wk.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser login(SysUserVo sysUserVo) {
		//对输入的密码进行MD5加密
		String md5Pwd = DigestUtils.md5DigestAsHex(sysUserVo.getPwd().getBytes());
		Map<String,Object> map = new HashMap<>();
		map.put("loginname",sysUserVo.getLoginname());
		map.put("pwd",md5Pwd);
		List<SysUser> sysUsers = sysUserMapper.selectByMap(map);
		if (sysUsers.size()>0) {
			return sysUsers.get(0);
		}else {
			return null;
		}
	}
}
