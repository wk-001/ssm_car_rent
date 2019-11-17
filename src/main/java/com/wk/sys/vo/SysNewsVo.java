package com.wk.sys.vo;

import com.wk.sys.pojo.SysNews;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysNewsVo extends SysNews {

    private Integer page;           //当前是第几页

    private Integer limit;          //每页多少条

    private Integer[] ids;			//接收多个ID，用于批量删除

    //前台接收数据传递到后台
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
