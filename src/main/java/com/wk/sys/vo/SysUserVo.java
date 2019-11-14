package com.wk.sys.vo;

import com.wk.sys.pojo.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserVo extends SysUser {

		private Integer page;           //当前是第几页

		private Integer limit;          //每页多少条

		private Integer[] ids;			//接收多个ID，用于批量删除

}
