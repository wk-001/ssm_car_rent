package com.wk.sys.vo;

import com.wk.sys.pojo.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleVo extends SysRole {

	private Integer page;           //当前是第几页

	private Integer limit;          //每页多少条

	private Integer[] ids;			//接收多个角色ID，用于批量删除

}
