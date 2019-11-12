package com.wk.sys.service;

import com.wk.sys.pojo.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.sys.utils.DataGrid;
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

    /**
     * 查询所有菜单并分页
     * @param sysMenuVo
     * @return
     */
    DataGrid queryAllMenu(SysMenuVo sysMenuVo);

    /**
     * 添加菜单
     * @param sysMenuVo
     * @return
     */
    int addMenu(SysMenuVo sysMenuVo);

    /**
     * 更新菜单信息
     * @param sysMenuVo
     * @return
     */
	int updateMenu(SysMenuVo sysMenuVo);

    /**
     * 查询是否有子节点
     * @param id
     * @return
     */
    int checkChildCount(Integer id);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    int deleteMenu(Integer id);
}
