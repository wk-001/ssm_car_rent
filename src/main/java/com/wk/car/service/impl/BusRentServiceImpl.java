package com.wk.car.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.car.pojo.BusRent;
import com.wk.car.dao.BusRentMapper;
import com.wk.car.service.BusRentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.car.vo.BusRentVo;
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
public class BusRentServiceImpl extends ServiceImpl<BusRentMapper, BusRent> implements BusRentService {

    @Autowired
    private BusRentMapper busRentMapper;

    @Override
    public DataGrid queryAllRent(BusRentVo busRentVo) {
        Page<Object> page = PageHelper.startPage(busRentVo.getPage(), busRentVo.getLimit());
        List<BusRent> list = busRentMapper.queryAllRent(busRentVo);
        return new DataGrid(page.getTotal(),list);
    }
}
