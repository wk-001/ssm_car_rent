package com.wk.car.dao;

import com.wk.car.pojo.BusCheck;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wk.car.vo.BusCheckVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface BusCheckMapper extends BaseMapper<BusCheck> {

	List<BusCheck> queryAllCheck(BusCheckVo checkVo);
}
