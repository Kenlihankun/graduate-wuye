package com.cy.summary.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

@Data
@TableName("summary")
public class summary implements Serializable {
    @TableId(type = IdType.AUTO)
    @NotNull(groups = {feePay.class})
    private Long summaryId;


    /**
     * 用户id
     */
    private Long userId;


    /**
     * 总结
     */
    private String summary;

    /**
     * 开始时间
     */

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endTime;


}
