package com.cy.chat.deepseek.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {
    @JsonProperty("role")
    private final String role;

    @JsonProperty("content")
    private final String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // Getters
    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}
