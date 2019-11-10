package com.wk.sys.utils;

import lombok.Data;

import java.util.List;

/**
 * 封装数据表格需要的数据对象
 */
@Data
public class DataGrid {

    private Integer code=0;

    private String msg;

    private Long count;

    private List<?> data;

    public DataGrid(List<?> data) {
        this.data = data;
    }

    public DataGrid(Long count, List<?> data) {
        this.count = count;
        this.data = data;
    }
}
