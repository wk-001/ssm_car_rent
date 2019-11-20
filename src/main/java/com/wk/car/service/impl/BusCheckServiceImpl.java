package com.wk.car.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.car.dao.BusCarMapper;
import com.wk.car.dao.BusCheckMapper;
import com.wk.car.dao.BusCustomerMapper;
import com.wk.car.dao.BusRentMapper;
import com.wk.car.pojo.BusCar;
import com.wk.car.pojo.BusCheck;
import com.wk.car.pojo.BusCustomer;
import com.wk.car.pojo.BusRent;
import com.wk.car.service.BusCheckService;
import com.wk.car.vo.BusCheckVo;
import com.wk.sys.constast.SysConstast;
import com.wk.sys.pojo.SysUser;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.RandomUtils;
import com.wk.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@Service
public class BusCheckServiceImpl extends ServiceImpl<BusCheckMapper, BusCheck> implements BusCheckService {

	@Autowired
	private BusCheckMapper busCheckMapper;

	@Autowired
	private BusRentMapper rentMapper;

	@Autowired
	private BusCarMapper carMapper;

	@Autowired
	private BusCustomerMapper customerMapper;

	@Override
	public Map<String, Object> initCheckFormData(String rentid) {
		SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
		//出租单信息
		BusRent rent = rentMapper.selectById(rentid);
		//车辆信息
		BusCar car = carMapper.selectById(rent.getCarnumber());
		//客户信息
		BusCustomer customer = customerMapper.selectById(rent.getIdentity());
		//组装检查单信息
		BusCheck check = new BusCheck();
		check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
		check.setCheckdate(new Date());
		check.setRentid(rentid);
		check.setOpername(user.getRealname());

		Map<String, Object> map = new HashMap<>();
		map.put("rent",rent);
		map.put("car",car);
		map.put("customer",customer);
		map.put("check",check);

		return map;
	}

	@Override
	public void saveCheck(BusCheck check) {
		//更改出租单出租状态
		BusRent rent = rentMapper.selectById(check.getRentid());
		rent.setRentflag(SysConstast.RENT_BACK_TRUE);
		rentMapper.updateById(rent);

		//更改汽车出租状态
		BusCar car = new BusCar();
		car.setCarnumber(rent.getCarnumber());
		car.setIsrenting(SysConstast.RENT_CAR_FALSE);
		carMapper.updateById(car);

		busCheckMapper.insert(check);
	}

	@Override
	public DataGrid queryAllCheck(BusCheckVo checkVo) {
		Page<Object> page = PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
		List<BusCheck> list = busCheckMapper.queryAllCheck(checkVo);
		return new DataGrid(page.getTotal(),list);
	}
}
