package com.wk.car.service;

import com.wk.car.pojo.BusRent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.car.vo.BusRentVo;
import com.wk.sys.utils.DataGrid;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface BusRentService extends IService<BusRent> {

    DataGrid queryAllRent(BusRentVo busRentVo);
}
