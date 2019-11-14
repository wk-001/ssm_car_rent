package com.wk.sys.controller;


import com.wk.sys.service.SysRoleUserService;
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
@RequestMapping("roleUser")
public class SysRoleUserController {

	@Autowired
	private SysRoleUserService sysRoleUserService;

	@RequestMapping("editRoleUser")
	public ResultObj editRoleUser(SysUserVo sysUserVo){
		try {
			sysRoleUserService.editRoleUser(sysUserVo);
			return ResultObj.OPERAT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.OPERAT_FAIL;
	}
}
