package com.wk.sys.service;

import com.wk.sys.pojo.Stat;

import java.util.List;
import java.util.Map;

public interface StatService {


    List<Stat> queryCustomerArea();

	Map<String, Object> empAnnualSaleStat(String year);

	Map<String, Object> companyAnnualSaleStat(String year);
}
