package com.wk.sys.controller;


import com.wk.sys.constast.SysConstast;
import com.wk.sys.pojo.SysMenu;
import com.wk.sys.pojo.SysUser;
import com.wk.sys.service.SysMenuService;
import com.wk.sys.utils.*;
import com.wk.sys.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  菜单管理控制器
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("initLeftMenu")
    public List<TreeNode> initLeftMenu(SysMenuVo sysMenuVo){    //直接根据用户ID查询菜单？？？
        //得到当前登录用户的ID
        SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
        List<SysMenu> list = null;
        sysMenuVo.setAvailable(SysConstast.AVAILABLE_TRUE);     //查询可用状态的菜单
        if(user.getType()== SysConstast.USER_TYPE_SUPER){      //如果是超级管理员直接查询所有菜单，
            list = sysMenuService.queryMenuList(sysMenuVo);
        }else{                      //系统用户根据用户ID查询可用菜单
            list = sysMenuService.queryMenuListByUserId(sysMenuVo,user.getUserid());
        }

        //把list中的数据放入到nodes中
        List<TreeNode> nodes = getTreeNodeList(list);

        Integer topPid = 1;     //设置父节点
        return TreeNodeUtils.builder(nodes,topPid);

    }

    /**
     * 菜单管理需要的菜单树
     */
    @RequestMapping("menuTree")
    public DataGrid menuTree(SysMenuVo sysMenuVo){
        sysMenuVo.setAvailable(SysConstast.AVAILABLE_TRUE);     //查询可用状态的菜单
        List<SysMenu> list = sysMenuService.queryMenuList(sysMenuVo);
        List<TreeNode> nodes = getTreeNodeList(list);
        return new DataGrid(nodes);
    }

    /**
     * 菜单数据表格
     */
    @RequestMapping("menuDataList")
    public DataGrid menuDataList(SysMenuVo sysMenuVo){
        sysMenuVo.setAvailable(SysConstast.AVAILABLE_TRUE);     //查询可用状态的菜单
        return sysMenuService.queryAllMenu(sysMenuVo);
    }


    private List<TreeNode> getTreeNodeList(List<SysMenu> list) {
        List<TreeNode> nodes = new ArrayList<>();
        for (SysMenu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread()== SysConstast.SPREAD_TRUE?true:false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }
        return nodes;
    }

    /**
     * 添加菜单
     * @param sysMenuVo
     * @return
     */
    @RequestMapping("add")
    public ResultObj add(SysMenuVo sysMenuVo){
        try {
            int index = sysMenuService.addMenu(sysMenuVo);
            if(index>0){
                return ResultObj.OPERAT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 修改菜单
     * @param sysMenuVo
     * @return
     */
    @RequestMapping("update")
    public ResultObj update(SysMenuVo sysMenuVo){
        try {
            int index = sysMenuService.updateMenu(sysMenuVo);
            if(index>0){
                return ResultObj.OPERAT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 查询是否有子节点
     * @param id
     * @return
     */
    @RequestMapping("checkChildCount")
    public int checkChildCount(Integer id){
        return sysMenuService.checkChildCount(id);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public ResultObj delete(Integer id){
        try {
            int i = sysMenuService.deleteMenu(id);
            if(i>0){
                return ResultObj.OPERAT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 角色分配菜单需要带有复选框的菜单树
     * @return
     */
    @RequestMapping("roleMenuTree")
    public DataGrid roleMenuTree(Integer roleid){
        return sysMenuService.roleMenuTree(roleid);
    }
}
