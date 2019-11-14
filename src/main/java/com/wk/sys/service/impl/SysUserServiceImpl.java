package com.wk.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wk.sys.constast.SysConstast;
import com.wk.sys.dao.SysRoleMapper;
import com.wk.sys.dao.SysRoleUserMapper;
import com.wk.sys.dao.SysUserMapper;
import com.wk.sys.pojo.SysRole;
import com.wk.sys.pojo.SysUser;
import com.wk.sys.service.SysUserService;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public SysUser login(SysUserVo sysUserVo) {
		//对输入的密码进行MD5加密
		String md5Pwd = DigestUtils.md5DigestAsHex(sysUserVo.getPwd().getBytes());
		Map<String,Object> map = new HashMap<>();
		map.put("loginname",sysUserVo.getLoginname());
		map.put("pwd",md5Pwd);
		List<SysUser> sysUsers = sysUserMapper.selectByMap(map);
		if (sysUsers.size()>0) {
			return sysUsers.get(0);
		}else {
			return null;
		}
	}

	@Override
	public DataGrid queryUserList(SysUserVo sysUserVo) {
		Page<Object> page = PageHelper.startPage(sysUserVo.getPage(), sysUserVo.getLimit());
		List<SysUser> list = sysUserMapper.queryAllUser(sysUserVo);
		return new DataGrid(page.getTotal(),list);
	}

	@Override
	public int addUser(SysUserVo sysUserVo) {
		//用户管理页面添加用户设置默认密码
		String md5Pwd = DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes());
		sysUserVo.setPwd(md5Pwd);
		//设置用户类型，除管理员外类型都是2
		sysUserVo.setType(SysConstast.SYS_TWO);
		return sysUserMapper.insert(sysUserVo);
	}

	@Override
	public int updateUser(SysUserVo sysUserVo) {
		return sysUserMapper.updateById(sysUserVo);
	}

	@Override
	public int deleteUser(Integer id) {
		Map<String,Object> map = new HashMap<>();
		map.put("uid",id);
		sysRoleUserMapper.deleteByMap(map);
		return sysUserMapper.deleteById(id);
	}

	@Override
	public int batchDeleteUser(Integer[] ids) {
		int i = 0;
		for (Integer id : ids) {
			i+=deleteUser(id);
		}
		return i;
	}

	@Override
	public int resetPwd(Integer id) {
		String md5Pwd = DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes());
		SysUser sysUser = new SysUser();
		sysUser.setUserid(id);
		sysUser.setPwd(md5Pwd);
		return sysUserMapper.updateById(sysUser);
	}

	@Override
	public DataGrid selectUserRole(Integer userid) {
		//查询所有可用角色
		SysRole sysRole = new SysRole();
		sysRole.setAvailable(SysConstast.AVAILABLE_TRUE);
		List<SysRole> roleList= sysRoleMapper.queryAllRole(sysRole);

		//根据userID查询该用户已有的角色
		List<SysRole> roleUserList = sysRoleMapper.selectByUid(userid);

		//组装数据，这里只需要三个字段，如果字段多了就在vo对象添加属性
		List<Map<String,Object>> list = new ArrayList<>();

		for (SysRole role : roleList) {
			boolean LAY_CHECKED = false;
			for (SysRole sysRoles : roleUserList) {
				if(role.getRoleid()==sysRoles.getRoleid()){
					LAY_CHECKED = true;
				}
			}
				Map<String,Object> map = new HashMap<>();
				map.put("roleid",role.getRoleid());
				map.put("rolename",role.getRolename());
				map.put("roledesc",role.getRoledesc());
				map.put("LAY_CHECKED",LAY_CHECKED);			//回显选中
				list.add(map);
		}
		return new DataGrid(list);
	}
}
