package com.wk.sys.service;

import com.wk.sys.pojo.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.sys.vo.SysRoleVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

	void editRoleMenu(SysRoleVo sysRoleVo);
}
