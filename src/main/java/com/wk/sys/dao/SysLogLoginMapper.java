package com.wk.sys.dao;

import com.wk.sys.pojo.SysLogLogin;
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
public interface SysLogLoginMapper extends BaseMapper<SysLogLogin> {

    List<SysLogLogin> queryAllLogInfo(SysLogLogin sysLogLogin);
}
