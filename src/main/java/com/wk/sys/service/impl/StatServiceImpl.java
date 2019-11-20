package com.wk.sys.service.impl;

import com.wk.car.dao.BusCustomerMapper;
import com.wk.sys.dao.SysUserMapper;
import com.wk.sys.pojo.Stat;
import com.wk.sys.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private BusCustomerMapper customerMapper;

    @Override
    public List<Stat> queryCustomerArea() {
        return customerMapper.queryCustomerArea();
    }
}
