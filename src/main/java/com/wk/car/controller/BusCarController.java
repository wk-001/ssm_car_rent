package com.wk.car.controller;


import com.wk.car.pojo.BusCar;
import com.wk.car.service.BusCarService;
import com.wk.car.vo.BusCarVo;
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
@RequestMapping("busCar")
public class BusCarController {

    @Autowired
    private BusCarService busCarService;

    /**
     * 查询所有车辆
     * @param busCarVo
     * @return
     */
    @RequestMapping("carList")
    public DataGrid carList(BusCarVo busCarVo){
        return busCarService.queryAllCar(busCarVo);
    }

    /**
     * 添加车辆
     * @param busCar
     * @return
     */
    @RequestMapping("addCar")
    public ResultObj addCar(BusCar busCar){
        busCar.setCreatetime(new Date());
        try {
            busCarService.save(busCar);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 根据ID删除车辆
     * @param id
     * @return
     */
    @RequestMapping("deleteById")
    public ResultObj deleteById(String id){
        try {
            busCarService.removeById(id);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 根据ID批量删除车辆
     * @param ids
     * @return
     */
    @RequestMapping("batchDelete")
    public ResultObj batchDelete(String[] ids){
        try {
            busCarService.removeByIds(Arrays.asList(ids));
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }

    /**
     * 根据ID修改车辆
     * @param busCar
     * @return
     */
    @RequestMapping("updateById")
    public ResultObj updateById(BusCar busCar){
        try {
            busCarService.updateById(busCar);
            return ResultObj.OPERAT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObj.OPERAT_FAIL;
    }
    
}
