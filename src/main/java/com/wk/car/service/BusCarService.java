package com.wk.car.service;

import com.wk.car.pojo.BusCar;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.car.vo.BusCarVo;
import com.wk.sys.utils.DataGrid;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface BusCarService extends IService<BusCar> {

    /**
     * 查询所有顾客
     */
    DataGrid queryAllCar(BusCarVo busCarVo);
}
