package com.wk.sys.service.impl;

import com.wk.sys.pojo.SysRoleUser;
import com.wk.sys.dao.SysRoleUserMapper;
import com.wk.sys.service.SysRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;

	@Override
	public void editRoleUser(SysUserVo sysUserVo) {
		//删除用户之前拥有的角色
		Map<String,Object> map = new HashMap<>();
		map.put("uid",sysUserVo.getUserid());
		sysRoleUserMapper.deleteByMap(map);
		if(sysUserVo.getIds()!=null && sysUserVo.getIds().length>0){
			sysRoleUserMapper.batchInsert(sysUserVo);
		}
	}
}
