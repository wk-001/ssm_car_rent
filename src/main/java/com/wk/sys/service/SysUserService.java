package com.wk.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.sys.pojo.SysUser;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.vo.SysUserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * 用户登录
	 * @param sysUserVo
	 * @return
	 */
	SysUser login(SysUserVo sysUserVo);

	/**
	 * 表格数据，查询所有用户
	 * @param sysUserVo
	 * @return
	 */
	DataGrid queryUserList(SysUserVo sysUserVo);

	/**
	 * 添加用户
	 * @param sysUserVo
	 */
	int addUser(SysUserVo sysUserVo);

	/**
	 * 修改用户信息
	 * @param sysUserVo
	 */
	int updateUser(SysUserVo sysUserVo);

	/**
	 * 根据ID删除用户表、用户用户表、用户权限表
	 * @param id
	 */
	int deleteUser(Integer id);

	/**
	 * 批量删除用户表、用户用户表、用户权限表
	 * @param ids
	 * @return
	 */
	int batchDeleteUser(Integer[] ids);

	/**
	 * 重置为初始密码
	 * @param id
	 */
	int resetPwd(Integer id);

	DataGrid selectUserRole(Integer userid);
}
