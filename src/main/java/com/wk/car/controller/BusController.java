package com.wk.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bus")
public class BusController {

    /**
     * 跳转到客户管理页面
     * @return
     */
    @RequestMapping("toCustomerMain")
    public String toCustomerMain(){
        return "business/customer/customerMain";
    }

    /**
     * 跳转到车辆管理页面
     * @return
     */
    @RequestMapping("toCaMain")
    public String toCaMain(){
        return "business/car/carMain";
    }
}
