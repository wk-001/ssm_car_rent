package com.wk.car.pojo;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wk
 * @since 2019-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 身份证
     */
    private String identity;

    /**
     * 姓名
     */
    private String custname;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 职位
     */
    private String career;

    private LocalDateTime createtime;


}
