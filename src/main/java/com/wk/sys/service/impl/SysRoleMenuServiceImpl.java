package com.wk.sys.service.impl;

import com.wk.sys.pojo.SysRoleMenu;
import com.wk.sys.dao.SysRoleMenuMapper;
import com.wk.sys.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.sys.vo.SysRoleVo;
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
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	public void editRoleMenu(SysRoleVo sysRoleVo) {
		Map<String,Object> map = new HashMap<>();
		map.put("rid",sysRoleVo.getRoleid());
		//先根据角色删除之前的权限
		sysRoleMenuMapper.deleteByMap(map);
		//再重新保存角色的权限
		sysRoleMenuMapper.batchInsert(sysRoleVo);
	}
}
