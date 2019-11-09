package com.wk.sys.service;

import com.wk.sys.pojo.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.sys.vo.SysMenuVo;

import java.util.List;

/**
 * <p>
 *  菜单管理接口
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 查询所有菜单
     * @param sysMenuVo
     * @return
     */
    List<SysMenu> queryMenuList(SysMenuVo sysMenuVo);

    /**
     * 根据用户ID查询该用户可用的菜单
     * @param sysMenuVo
     * @return
     */
    List<SysMenu> queryMenuListByUserId(SysMenuVo sysMenuVo,Integer userId);
}
