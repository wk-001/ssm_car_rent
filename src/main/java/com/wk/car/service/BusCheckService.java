package com.wk.car.service;

import com.wk.car.pojo.BusCheck;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.car.vo.BusCheckVo;
import com.wk.sys.utils.DataGrid;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface BusCheckService extends IService<BusCheck> {

	Map<String, Object> initCheckFormData(String rentid);

	void saveCheck(BusCheck check);

	DataGrid queryAllCheck(BusCheckVo checkVo);
}
