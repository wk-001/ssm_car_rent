package com.wk.sys.dao;

import com.wk.sys.pojo.SysNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysNewsMapper extends BaseMapper<SysNews> {

    List<SysNews> queryAllNews(SysNews sysNews);
}
