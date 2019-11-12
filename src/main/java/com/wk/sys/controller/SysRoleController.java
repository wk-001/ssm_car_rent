package com.wk.sys.controller;


import com.wk.sys.service.SysRoleService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.ResultObj;
import com.wk.sys.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  角色管理控制器
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色列表
     * @param sysRoleVo
     * @return
     */
    @RequestMapping("roleList")
    public DataGrid roleList(SysRoleVo sysRoleVo){
        return sysRoleService.queryRoleList(sysRoleVo);
    }

    /**
     * 添加角色
     * @param sysRoleVo
     * @return
     */
    @RequestMapping("addRole")
    public ResultObj addRole(SysRoleVo sysRoleVo){
        try {
            int i = sysRoleService.addRole(sysRoleVo);
            if (i > 0) {
                return ResultObj.OPERAT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 修改角色
     * @param sysRoleVo
     * @return
     */
    @RequestMapping("updateRole")
    public ResultObj updateRole(SysRoleVo sysRoleVo){
        try {
            int i = sysRoleService.updateRole(sysRoleVo);
            if (i > 0) {
                return ResultObj.OPERAT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 删除角色
     * @param sysRoleVo
     * @return
     */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(SysRoleVo sysRoleVo){
        try {
            int i = sysRoleService.deleteRole(sysRoleVo.getRoleid());
            if (i > 0) {
                return ResultObj.OPERAT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 批量删除角色
     * @param sysRoleVo
     * @return
     */
    @RequestMapping("batchDeleteRole")
    public ResultObj batchDeleteRole(SysRoleVo sysRoleVo){
        try {
            int i = sysRoleService.batchDeleteRole(sysRoleVo.getIds());
            if (i > 0) {
                return ResultObj.OPERAT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }




}
