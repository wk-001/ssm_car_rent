package com.wk.sys.controller;


import com.wk.sys.service.SysRoleMenuService;
import com.wk.sys.utils.ResultObj;
import com.wk.sys.vo.SysRoleVo;
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
@RequestMapping("/roleMenu")
public class SysRoleMenuController {

	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@RequestMapping("editRoleMenu")
	public ResultObj editRoleMenu(SysRoleVo sysRoleVo){
		try {
			sysRoleMenuService.editRoleMenu(sysRoleVo);
			return ResultObj.OPERAT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.OPERAT_FAIL;
		}
	}
}
