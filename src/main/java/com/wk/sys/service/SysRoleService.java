package com.wk.sys.service;

import com.wk.sys.pojo.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.vo.SysRoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 查询所有角色
	 * @param sysRoleVo
	 * @return
	 */
	List<SysRole> queryAllRole(SysRoleVo sysRoleVo);

	/**
	 * 表格数据，查询所有角色
	 * @param sysRoleVo
	 * @return
	 */
	DataGrid queryRoleList(SysRoleVo sysRoleVo);

	/**
	 * 添加角色
	 * @param sysRoleVo
	 */
	int addRole(SysRoleVo sysRoleVo);

	/**
	 * 修改角色信息
	 * @param sysRoleVo
	 */
	int updateRole(SysRoleVo sysRoleVo);

	/**
	 * 根据ID删除角色表、角色用户表、角色权限表
	 * @param id
	 */
	int deleteRole(Integer id);

	/**
	 * 批量删除角色表、角色用户表、角色权限表
	 * @param ids
	 * @return
	 */
	int batchDeleteRole(Integer[] ids);
}
