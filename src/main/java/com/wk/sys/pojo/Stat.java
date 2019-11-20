package com.wk.sys.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 统计类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Stat {

    private String name;

    private Double value;

}
