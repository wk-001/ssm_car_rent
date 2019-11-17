package com.wk.sys.service;

import com.wk.sys.pojo.SysNews;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.vo.SysNewsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysNewsService extends IService<SysNews> {

    /**
     * 查询所有公告
     * @param sysNewsVo
     * @return
     */
    DataGrid queryAllNews(SysNewsVo sysNewsVo);

    /**
     * 添加公告
     * @param sysNews
     */
    void addNews(SysNews sysNews);

    /**
     * 修改公告
     * @param sysNews
     */
    void updateNews(SysNews sysNews);

    /**
     * 根据ID删除公告
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除公告
     * @param ids
     */
    void batchDelete(Integer[] ids);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    SysNews getNewsById(Integer id);
}
