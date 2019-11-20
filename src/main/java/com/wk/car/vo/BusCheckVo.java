package com.wk.car.vo;

import com.wk.car.pojo.BusCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusCheckVo extends BusCheck {

    private Integer page;           //当前是第几页

    private Integer limit;          //每页多少条

    //前台接收数据传递到后台
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
