package com.wk.sys.dao;

import com.wk.sys.pojo.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

	List<SysRole> queryAllRole(SysRole sysRole);

	List<SysRole> selectByUid(Integer userid);
}
