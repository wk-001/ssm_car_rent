package com.wk.sys.service.impl;

import com.wk.car.dao.BusCustomerMapper;
import com.wk.car.dao.BusRentMapper;
import com.wk.sys.pojo.Stat;
import com.wk.sys.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {

	@Autowired
	private BusCustomerMapper customerMapper;

	@Autowired
	private BusRentMapper rentMapper;

	@Override
	public List<Stat> queryCustomerArea() {
		return customerMapper.queryCustomerArea();
	}

	@Override
	public Map<String, Object> empAnnualSaleStat(String year) {
		List<Stat> list = rentMapper.empAnnualSaleStat(year);
		Map<String, Object> map = getMap(list);
		return map;
	}

	@Override
	public Map<String, Object> companyAnnualSaleStat(String year) {
		List<Stat> list = rentMapper.companyAnnualSaleStat(year);
		Map<String, Object> map = getMap(list);
		return map;
	}

	private Map<String, Object> getMap(List<Stat> list) {
		Map<String, Object> map = new HashMap<>();
		List<String> names = new ArrayList<>();
		List<Double> values = new ArrayList<>();
		for (Stat stat : list) {
			names.add(stat.getName());
			values.add(stat.getValue());
		}
		map.put("name", names);
		map.put("value", values);
		return map;
	}
}
