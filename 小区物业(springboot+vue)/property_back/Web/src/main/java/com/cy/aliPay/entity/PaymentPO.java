package com.cy.aliPay.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cy.valid.feeAddOrEdit;
import com.cy.valid.feePay;
import com.cy.valid.listValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 停车费
 * @date 2022-01-22 10:32:32
 */
@Data
public class PaymentPO implements Serializable {
    @TableId(type = IdType.AUTO)
    @NotNull(groups = {feePay.class})
    private Long paymentId;



    /**
     * 缴费金额
     */
    @DecimalMin(value = "0")
    private BigDecimal amount;


    /**
     *  缴费状态
     */
    private Integer status;

    /**
     * 缴费时间
     */

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date payTime;

    /**
     * 支付描述
     */
    @TableField(exist = false)
    private String description;

    /**
     * 业主名字
     */
    @TableField(exist = false)
    private String loginName;

    /**
     * 业主手机号
     */
    @TableField(exist = false)
    private String phone;

}
