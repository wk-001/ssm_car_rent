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
public class BusRent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rentid", type = IdType.INPUT)
    private String rentid;

    private Double price;

    private LocalDateTime begindate;

    private LocalDateTime returndate;

    private Integer rentflag;

    private String identity;

    private String carnumber;

    private String opername;

    //后台生成指定格式日期显示到前台
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;


}
