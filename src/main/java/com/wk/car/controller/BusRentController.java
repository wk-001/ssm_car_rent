package com.wk.car.controller;


import com.wk.car.pojo.BusCar;
import com.wk.car.pojo.BusRent;
import com.wk.car.service.BusCarService;
import com.wk.car.service.BusRentService;
import com.wk.car.vo.BusRentVo;
import com.wk.sys.constast.SysConstast;
import com.wk.sys.pojo.SysUser;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.RandomUtils;
import com.wk.sys.utils.ResultObj;
import com.wk.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("rent")
public class BusRentController {

	@Autowired
	private BusRentService rentService;

	@Autowired
	private BusCarService carService;

	/**
	 * 初始化出租页面表单
	 * @param busRentVo
	 * @return
	 */
	@RequestMapping("initRent")
	public BusRentVo initRent(BusRentVo busRentVo){
		//生成出租单号
		busRentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CZ));
		//设置起租时间
		busRentVo.setBegindate(new Date());
		//设置操作员
		SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
		busRentVo.setOpername(user.getRealname());
		return busRentVo;
	}

	/**
	 * 添加出租单
	 * @param busRent
	 * @return
	 */
	@RequestMapping("addRent")
	public ResultObj addRent(BusRent busRent){
		busRent.setRentflag(SysConstast.RENT_BACK_FALSE);
		busRent.setCreatetime(new Date());
		//汽车状态改为已出租
		BusCar car = new BusCar();
		car.setCarnumber(busRent.getCarnumber());
		car.setIsrenting(SysConstast.RENT_CAR_TRUE);
		try {
			carService.updateById(car);
			rentService.save(busRent);
			return ResultObj.OPERAT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultObj.OPERAT_FAIL;
	}

	@RequestMapping("rentList")
	public DataGrid rentList(BusRentVo busRentVo){
		return rentService.queryAllRent(busRentVo);
	}

}
