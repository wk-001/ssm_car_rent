package com.wk.sys.vo;

import com.wk.sys.pojo.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenuVo extends SysMenu {

    private Integer page;           //当前是第几页

    private Integer limit;          //每页多少条
}
