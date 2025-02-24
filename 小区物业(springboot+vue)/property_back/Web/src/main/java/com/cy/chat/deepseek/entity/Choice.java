package com.cy.chat.deepseek.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Choice {
    @JsonProperty("index")
    private int index;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("finish_reason")  // 添加 finish_reason 字段
    private String finishReason;  // 对应 JSON 中的 finish_reason 字段
}