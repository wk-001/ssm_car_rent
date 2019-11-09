package com.wk.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wk.sys.pojo.SysMenu;
import com.wk.sys.dao.SysMenuMapper;
import com.wk.sys.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.sys.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  菜单管理接口实现类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> queryMenuList(SysMenuVo sysMenuVo) {

        return sysMenuMapper.queryMenuList(sysMenuVo);
    }

    @Override
    public List<SysMenu> queryMenuListByUserId(SysMenuVo sysMenuVo, Integer userId) {
        return sysMenuMapper.queryMenuList(sysMenuVo);
    }
}