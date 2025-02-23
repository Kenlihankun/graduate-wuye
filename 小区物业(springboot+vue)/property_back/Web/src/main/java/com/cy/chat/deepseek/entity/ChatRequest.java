package com.cy.chat.deepseek.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ChatRequest {
    // Getters
    private final String model = "deepseek-r1:7b";

    @JsonProperty("messages")
    private final List<Message> messages;

    private final double temperature = 0.7;
    private final double top_p = 0.9;
    private final int max_tokens = 512;
    private final double frequency_penalty = 0.5;
    private final double presence_penalty = 0.5;

    public ChatRequest(String userMessage) {
        this.messages = Arrays.asList(new Message("user", userMessage)); // 使用 Arrays.asList()
    }

}