package com.wk.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.sys.pojo.SysLogLogin;
import com.wk.sys.dao.SysLogLoginMapper;
import com.wk.sys.service.SysLogLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.vo.SysLogLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
public class SysLogLoginServiceImpl extends ServiceImpl<SysLogLoginMapper, SysLogLogin> implements SysLogLoginService {

    @Autowired
    private SysLogLoginMapper sysLogLoginMapper;

    @Override
    public DataGrid queryAllLog(SysLogLoginVo sysLogLoginVo) {
        Page<Object> page = PageHelper.startPage(sysLogLoginVo.getPage(), sysLogLoginVo.getLimit());
        List<SysLogLogin> list = sysLogLoginMapper.queryAllLogInfo(sysLogLoginVo);
        return new DataGrid(page.getTotal(),list);
    }

    @Override
    public void addLog(SysLogLogin sysLogLogin) {
        sysLogLoginMapper.insert(sysLogLogin);
    }

    @Override
    public void deleteById(Integer id) {
        sysLogLoginMapper.deleteById(id);
    }

    @Override
    public void batchDelete(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        sysLogLoginMapper.deleteBatchIds(list);
    }
}
