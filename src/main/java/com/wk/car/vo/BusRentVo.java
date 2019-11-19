package com.wk.car.vo;

import com.wk.car.pojo.BusRent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusRentVo extends BusRent {

    private Integer page;           //当前是第几页

    private Integer limit;          //每页多少条
}
