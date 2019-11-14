package com.wk.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.sys.constast.SysConstast;
import com.wk.sys.dao.SysMenuMapper;
import com.wk.sys.dao.SysRoleMenuMapper;
import com.wk.sys.pojo.SysMenu;
import com.wk.sys.service.SysMenuService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.TreeNode;
import com.wk.sys.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> queryMenuList(SysMenuVo sysMenuVo) {
        return sysMenuMapper.queryMenuList(sysMenuVo);
    }

    @Override
    public List<SysMenu> queryMenuListByUserId(SysMenuVo sysMenuVo, Integer userId) {
        return sysMenuMapper.queryMenuByUid(sysMenuVo.getAvailable(),userId);
    }

    @Override
    public DataGrid queryAllMenu(SysMenuVo sysMenuVo) {
        Page<Object> page = PageHelper.startPage(sysMenuVo.getPage(), sysMenuVo.getLimit());
        List<SysMenu> list = sysMenuMapper.queryMenuList(sysMenuVo);
        return new DataGrid(page.getTotal(),list);
    }

    @Override
    public int addMenu(SysMenuVo sysMenuVo) {
        return sysMenuMapper.insert(sysMenuVo);
    }

    @Override
    public int updateMenu(SysMenuVo sysMenuVo) {
        return sysMenuMapper.updateById(sysMenuVo);
    }

    @Override
    public int checkChildCount(Integer id) {
        return sysMenuMapper.queryMenuByPid(id);
    }

    @Override
    public int deleteMenu(Integer id) {
        Map<String,Object> map = new HashMap<>();
        map.put("mid",id);  //根据menuID删除role_menu表的数据
        sysRoleMenuMapper.deleteByMap(map);
        return sysMenuMapper.deleteById(id);
    }

	@Override
	public DataGrid roleMenuTree(Integer roleid) {
        //查询所有可用的菜单
        SysMenu sysMenuVo = new SysMenu();
        sysMenuVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<SysMenu> menuList = sysMenuMapper.queryMenuList(sysMenuVo);
        //根据角色ID查询菜单
        List<SysMenu> roleMenu = sysMenuMapper.queryMenuByRoleid(SysConstast.AVAILABLE_TRUE,roleid);

        List<TreeNode> data = new ArrayList<>();

        //判断节点是否选中
        for (SysMenu sysMenu : menuList) {
            String checkArr = SysConstast.SYS_ZERO+"";
            for (SysMenu menu : roleMenu) {
                if(menu.getId()==sysMenu.getId()){
                    checkArr = SysConstast.SYS_ONE+"";
                    break;
                }
            }
            data.add(new TreeNode(sysMenu.getId()
                    ,sysMenu.getPid()
                    ,sysMenu.getTitle()
                    ,sysMenu.getSpread()==1?true:false
                    ,checkArr));
        }


		return new DataGrid(data);
	}

}
