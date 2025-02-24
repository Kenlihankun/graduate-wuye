package com.cy.chat.deepseek.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;


@Getter
public class ChatResponse {
    @JsonProperty("id")
    private String id;  // 添加 id 字段

    @JsonProperty("object")
    private String object;  // 添加 object 字段

    @JsonProperty("created")
    private long created;  // 添加 created 字段

    @JsonProperty("model")
    private String model;  // 添加 model 字段

    @JsonProperty("system_fingerprint")
    private String systemFingerprint;  // 添加 system_fingerprint 字段

    @JsonProperty("choices")
    private List<Choice> choices;  // 关联 Choice 类

    @JsonProperty("usage")  // 如果需要，可以添加 usage 字段
    private Usage usage;

    // 如果需要，可以定义 Usage 类
    @Getter
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;

        @JsonProperty("completion_tokens")
        private int completionTokens;

        @JsonProperty("total_tokens")
        private int totalTokens;
    }
}