package com.wk.sys.controller;


import com.wk.sys.service.SysUserService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.ResultObj;
import com.wk.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@RestController
@RequestMapping("user")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 用户列表
	 * @param sysUserVo
	 * @return
	 */
	@RequestMapping("userList")
	public DataGrid userList(SysUserVo sysUserVo){
		return sysUserService.queryUserList(sysUserVo);
	}

	/**
	 * 添加用户
	 * @param sysUserVo
	 * @return
	 */
	@RequestMapping("addUser")
	public ResultObj addUser(SysUserVo sysUserVo){
		try {
			int i = sysUserService.addUser(sysUserVo);
			if (i > 0) {
				return ResultObj.OPERAT_SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.OPERAT_FAIL;
	}

	/**
	 * 修改用户
	 * @param sysUserVo
	 * @return
	 */
	@RequestMapping("updateUser")
	public ResultObj updateUser(SysUserVo sysUserVo){
		try {
			int i = sysUserService.updateUser(sysUserVo);
			if (i > 0) {
				return ResultObj.OPERAT_SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.OPERAT_FAIL;
	}

	/**
	 * 删除用户
	 * @param sysUserVo
	 * @return
	 */
	@RequestMapping("deleteUser")
	public ResultObj deleteUser(SysUserVo sysUserVo){
		try {
			int i = sysUserService.deleteUser(sysUserVo.getUserid());
			if (i > 0) {
				return ResultObj.OPERAT_SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.OPERAT_FAIL;
	}

	/**
	 * 批量删除用户
	 * @param sysUserVo
	 * @return
	 */
	@RequestMapping("batchDeleteUser")
	public ResultObj batchDeleteUser(SysUserVo sysUserVo){
		try {
			int i = sysUserService.batchDeleteUser(sysUserVo.getIds());
			if (i > 0) {
				return ResultObj.OPERAT_SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.OPERAT_FAIL;
	}

	/**
	 * 重置为初始密码
	 * @param id
	 * @return
	 */
	@RequestMapping("resetPwd")
	public ResultObj resetPwd(Integer id){
		try {
			int i = sysUserService.resetPwd(id);
			if (i > 0) {
				return ResultObj.OPERAT_SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.OPERAT_FAIL;
	}

	/**
	 * 查询全部角色和该用户已有角色
	 * @param userid
	 * @return
	 */
	@RequestMapping("userRole")
	public DataGrid userRole(Integer userid){
		return sysUserService.selectUserRole(userid);
	}

}
