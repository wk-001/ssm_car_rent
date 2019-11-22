package com.wk.car.service;

import com.wk.car.pojo.BusCustomer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.car.vo.BusCustomerVo;
import com.wk.sys.utils.DataGrid;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface BusCustomerService extends IService<BusCustomer> {

    /**
     * 查询所有顾客
     */
    DataGrid queryAllCustomer(BusCustomerVo busCustomerVo);

	List<BusCustomer> queryCustomerList(BusCustomer busCustomer);
}
