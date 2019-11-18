package com.wk.sys.utils;

import lombok.Data;

/**
 * 封装数据表格需要的数据对象
 */
@Data
public class DataGrid {

    private Integer code = 0;

    private String msg;

    private Long count;

    private Object data;

    public DataGrid(Object data) {
        this.data = data;
    }

    public DataGrid(Long count, Object data) {
        this.count = count;
        this.data = data;
    }
}
