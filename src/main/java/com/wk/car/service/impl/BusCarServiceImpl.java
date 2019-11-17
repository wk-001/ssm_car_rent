package com.wk.car.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.car.pojo.BusCar;
import com.wk.car.dao.BusCarMapper;
import com.wk.car.service.BusCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.car.vo.BusCarVo;
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
public class BusCarServiceImpl extends ServiceImpl<BusCarMapper, BusCar> implements BusCarService {

    @Autowired
    private BusCarMapper busCarMapper;

    @Override
    public DataGrid queryAllCar(BusCarVo busCarVo) {
        Page<Object> page = PageHelper.startPage(busCarVo.getPage(), busCarVo.getLimit());
        List<BusCar> list = busCarMapper.queryAllCar(busCarVo);
        return new DataGrid(page.getTotal(),list);
    }
}
