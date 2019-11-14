package com.wk.sys.dao;

import com.wk.sys.pojo.SysRoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wk.sys.vo.SysUserVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

	int batchInsert(SysUserVo sysUserVo);
}
