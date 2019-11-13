package com.wk.sys.dao;

import com.wk.sys.pojo.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wk.sys.vo.SysRoleVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

	void batchInsert(SysRoleVo sysRoleVo);
}
