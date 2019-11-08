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
public class BusRent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String rentid;

    private Double price;

    private LocalDateTime begindate;

    private LocalDateTime returndate;

    private Integer rentflag;

    private String identity;

    private String carnumber;

    private String opername;

    private LocalDateTime createtime;


}
