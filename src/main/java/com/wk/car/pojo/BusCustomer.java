package com.wk.car.pojo;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 经测试 非数组类型的主键不会在实体类中自动指定主键字段，需要手动添加@TableId注解指定主键字段
     */
    @TableId(value = "identity", type = IdType.INPUT)
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

    //后台生成指定格式日期显示到前台
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;


}
