package com.cy.notice.message.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_notice")
public class NoticeMessagePO implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long noticeMessageId;
    /**
     * 物业id
     */
    @NotNull
    private Long userId;

    /**
     * 标题
     */
    @NotBlank
    private String title;

    /**
     * 内容
     */
    @NotBlank
    private String noticeContent;

    /**
     * gpt建议
     */
    @NotBlank
    private String suggestion;

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}

