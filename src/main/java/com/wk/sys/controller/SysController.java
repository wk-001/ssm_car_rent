package com.wk.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器, 作用：路由页面
 */
@Controller
@RequestMapping("sys")
public class SysController {

    /**
     * 跳转到layui卡片面板布局
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager(){
        return "system/menu/menuManager";
    }

    /**
     * 跳转到frameset框架主体
     */
    @RequestMapping("toMenuMain")
    public String toMenuMain(){
        return "system/menu/menuMain";
    }

    /**
     * 跳转到frameset框架左侧菜单树页面
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/menuLeft";
    }

    /**
     * 跳转到frameset框架右侧菜单详情页面
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight(){
        return "system/menu/menuRight";
    }

    /**
     * 跳转到角色管理页面
     */
    @RequestMapping("toRoleMain")
    public String toRoleMain(){
        return "system/role/roleMain";
    }

    /**
     * 跳转到用户管理页面
     */
    @RequestMapping("toUserMain")
    public String toUserMain(){
        return "system/user/userMain";
    }
}
