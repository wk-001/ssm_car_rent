package com.wk.car.dao;

import com.wk.car.pojo.BusRent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wk.car.vo.BusRentVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface BusRentMapper extends BaseMapper<BusRent> {

    /**
     * 查询所有出租单
     */
    List<BusRent> queryAllRent(BusRentVo busRentVo);
}
