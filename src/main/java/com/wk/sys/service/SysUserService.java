package com.wk.sys.service;

import com.wk.sys.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.sys.vo.SysUserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
public interface SysUserService extends IService<SysUser> {

	SysUser login(SysUserVo sysUserVo);
}
