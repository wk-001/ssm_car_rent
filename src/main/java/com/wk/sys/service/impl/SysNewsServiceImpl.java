package com.wk.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.sys.pojo.SysNews;
import com.wk.sys.dao.SysNewsMapper;
import com.wk.sys.service.SysNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.vo.SysNewsVo;
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
public class SysNewsServiceImpl extends ServiceImpl<SysNewsMapper, SysNews> implements SysNewsService {

    @Autowired
    private SysNewsMapper sysNewsMapper;

    @Override
    public DataGrid queryAllNews(SysNewsVo sysNewsVo) {
        Page<Object> page = PageHelper.startPage(sysNewsVo.getPage(), sysNewsVo.getLimit());
        List<SysNews> list = sysNewsMapper.queryAllNews(sysNewsVo);
        return new DataGrid(page.getTotal(),list);
    }

    @Override
    public void addNews(SysNews sysNews) {
        sysNewsMapper.insert(sysNews);
    }

    @Override
    public void updateNews(SysNews sysNews) {
        sysNewsMapper.updateById(sysNews);
    }

    @Override
    public void deleteById(Integer id) {
        sysNewsMapper.deleteById(id);
    }

    @Override
    public void batchDelete(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        sysNewsMapper.deleteBatchIds(list);
    }

    @Override
    public SysNews getNewsById(Integer id) {
        return sysNewsMapper.selectById(id);
    }
}
