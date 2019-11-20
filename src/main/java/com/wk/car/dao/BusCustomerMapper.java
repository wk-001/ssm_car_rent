package com.wk.car.dao;

import com.wk.car.pojo.BusCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wk.sys.pojo.Stat;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface BusCustomerMapper extends BaseMapper<BusCustomer> {

    List<BusCustomer> queryAllCustomer(BusCustomer busCustomer);

    List<Stat> queryCustomerArea();

}
