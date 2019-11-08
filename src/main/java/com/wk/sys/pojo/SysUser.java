package com.wk.sys.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userid", type = IdType.AUTO)
    private Integer userid;

    private String loginname;

    private String identity;

    private String realname;

    /**
     * 0女1男
     */
    private Integer sex;

    private String address;

    private String phone;

    private String pwd;

    private String position;

    /**
     * 1，超级管理员,2，系统用户
     */
    private Integer type;

    private Integer available;


}
