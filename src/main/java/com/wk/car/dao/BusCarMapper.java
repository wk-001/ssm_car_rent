package com.wk.car.dao;

import com.wk.car.pojo.BusCar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface BusCarMapper extends BaseMapper<BusCar> {

    List<BusCar> queryAllCar(BusCar car);
}
