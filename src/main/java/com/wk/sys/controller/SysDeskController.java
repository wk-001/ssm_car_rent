package com.wk.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作台控制器
 */
@Controller
@RequestMapping("desk")
public class SysDeskController {

    /**
     * 跳转到工作台页面
     */
    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "system/main/deskManager";
    }
}
