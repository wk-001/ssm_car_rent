package com.wk.sys.dao;

import com.wk.sys.pojo.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> queryMenuList(SysMenu sysMenu);

	int queryMenuByPid(Integer id);

	List<SysMenu> queryMenuByRoleid(@Param("available") Integer available, @Param("roleid") Integer roleid);
}
