package com.wk.sys.controller;

import com.wk.sys.pojo.Stat;
import com.wk.sys.service.StatService;
import com.wk.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("stat")
public class StatController {

    @Autowired
    private StatService statService;

    /**
     * 跳转到客户地区统计页面
     * @return
     */
    @RequestMapping("toCustomerAreaStat")
    public String toCustomerAreaStat(){
        return "stat/customerAreaStat";
    }

    /**
     * 查询客户地区数据
     * @return
     */
    @RequestMapping("initCustomerAreaCharts")
    @ResponseBody
    public List<Stat> initCustomerAreaCharts(){
        return statService.queryCustomerArea();
    }
}
