package com.wk.car.controller;


import com.wk.car.pojo.BusCustomer;
import com.wk.car.service.BusCustomerService;
import com.wk.car.vo.BusCustomerVo;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@RestController
@RequestMapping("customer")
public class BusCustomerController {

    @Autowired
    private BusCustomerService customerService;

    /**
     * 查询所有客户
     * @param busCustomerVo
     * @return
     */
    @RequestMapping("customerList")
    public DataGrid customerList(BusCustomerVo busCustomerVo){
        return customerService.queryAllCustomer(busCustomerVo);
    }

    /**
     * 添加客户
     * @param busCustomer
     * @return
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(BusCustomer busCustomer){
        busCustomer.setCreatetime(new Date());
        try {
            customerService.save(busCustomer);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 根据ID删除客户
     * @param id
     * @return
     */
    @RequestMapping("deleteById")
    public ResultObj deleteById(String id){
        try {
            customerService.removeById(id);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 根据ID批量删除客户
     * @param ids
     * @return
     */
    @RequestMapping("batchDelete")
    public ResultObj batchDelete(String[] ids){
        try {
            customerService.removeByIds(Arrays.asList(ids));
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 根据ID修改客户
     * @param busCustomer
     * @return
     */
    @RequestMapping("updateById")
    public ResultObj updateById(BusCustomer busCustomer){
        try {
            customerService.updateById(busCustomer);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 根据ID查询客户
     * @param busCustomer
     * @return
     */
    @RequestMapping("getById")
    public ResultObj getById(BusCustomer busCustomer){
        BusCustomer customer = customerService.getById(busCustomer.getIdentity());
        if (customer != null) {
            return ResultObj.OPERAT_SUCCESS;
        }else {
            return ResultObj.OPERAT_FAIL;
        }
    }
}
