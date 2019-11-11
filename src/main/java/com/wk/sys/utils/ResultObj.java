package com.wk.sys.utils;

import com.wk.sys.constast.SysConstast;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ResultObj {

    private Integer code;

    private String msg;

    private ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static final ResultObj OPERAT_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.OPERAT_SUCCESS);
    public static final ResultObj OPERAT_FAIL =new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.OPERAT_FAIL);
}
