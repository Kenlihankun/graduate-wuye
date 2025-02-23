package com.cy.chat.deepseek.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatResponse {
    @JsonProperty("choices")
    private List<Choice> choices;

    public static class Choice {
        @JsonProperty("message")
        private Message message;

        public Message getMessage() {
            return message;
        }
    }
}