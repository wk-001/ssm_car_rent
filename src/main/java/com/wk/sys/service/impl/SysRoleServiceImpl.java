package com.wk.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.sys.dao.SysRoleMapper;
import com.wk.sys.dao.SysRoleMenuMapper;
import com.wk.sys.dao.SysRoleUserMapper;
import com.wk.sys.pojo.SysRole;
import com.wk.sys.service.SysRoleService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	public List<SysRole> queryAllRole(SysRoleVo sysRoleVo) {
		return sysRoleMapper.queryAllRole(sysRoleVo);
	}

	@Override
	public DataGrid queryRoleList(SysRoleVo sysRoleVo) {
		Page<Object> page = PageHelper.startPage(sysRoleVo.getPage(), sysRoleVo.getLimit());
		List<SysRole> list = sysRoleMapper.queryAllRole(sysRoleVo);
		return new DataGrid(page.getTotal(),list);
	}

	@Override
	public int addRole(SysRoleVo sysRoleVo) {
		return sysRoleMapper.insert(sysRoleVo);
	}

	@Override
	public int updateRole(SysRoleVo sysRoleVo) {
		return sysRoleMapper.updateById(sysRoleVo);
	}

	@Override
	public int deleteRole(Integer id) {
		Map<String,Object> map = new HashMap<>();
		map.put("rid",id);
		sysRoleUserMapper.deleteByMap(map);	//根据roleID删除role_user表的数据
		sysRoleMenuMapper.deleteByMap(map);	//根据roleID删除role_menu表的数据
		return sysRoleMapper.deleteById(id);
	}

	@Override
	public int batchDelete(List<Integer> ids) {
		return sysRoleMapper.deleteBatchIds(ids);
	}
}
