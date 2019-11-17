package com.wk.car.vo;

import com.wk.car.pojo.BusCustomer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusCustomerVo extends BusCustomer {

    private Integer page;           //当前是第几页

    private Integer limit;          //每页多少条

    private String[] ids;			//接收多个ID，用于批量删除
}
