package com.wk.car.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.car.pojo.BusCustomer;
import com.wk.car.dao.BusCustomerMapper;
import com.wk.car.service.BusCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.car.vo.BusCustomerVo;
import com.wk.sys.utils.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@Service
public class BusCustomerServiceImpl extends ServiceImpl<BusCustomerMapper, BusCustomer> implements BusCustomerService {

    @Autowired
    private BusCustomerMapper busCustomerMapper;

    @Override
    public DataGrid queryAllCustomer(BusCustomerVo busCustomerVo) {
        Page<Object> page = PageHelper.startPage(busCustomerVo.getPage(), busCustomerVo.getLimit());
        List<BusCustomer> list = busCustomerMapper.queryAllCustomer(busCustomerVo);
        return new DataGrid(page.getTotal(),list);
    }
}
