package com.wk.sys.controller;

import com.wk.sys.pojo.Stat;
import com.wk.sys.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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

    /**
     * 跳转到业务员年度销售额统计页面
     * @return
     */
    @RequestMapping("toEmpAnnualSalesStat")
    public String toEmpAnnualSalesStat(){
        return "stat/empAnnualSaleStat";
    }

    /**
     * 业务员年度销售额统计
     * @param year
     * @return
     */
    @RequestMapping("empAnnualSaleStat")
    @ResponseBody
    public Map<String,Object> empAnnualSaleStat(String year){
        return statService.empAnnualSaleStat(year);
    }

    /**
     * 跳转到公司年度月份销售额统计页面
     * @return
     */
    @RequestMapping("toCompanyAnnualSalesStat")
    public String toCompanyAnnualSalesStat(){
        return "stat/companyAnnualSaleStat";
    }

    /**
     * 公司年度月份销售额统计
     * @param year
     * @return
     */
    @RequestMapping("companyAnnualSaleStat")
    @ResponseBody
    public Map<String,Object> companyAnnualSaleStat(String year){
        return statService.companyAnnualSaleStat(year);
    }
}
