package com.cy.chat.deepseek.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor  // 添加无参构造函数
public class Message {
    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;

    // 你可以选择添加一个全参构造函数
    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
