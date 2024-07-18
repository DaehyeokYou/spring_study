package com.ydh.springstudy.payload;
/*
  {
    "messages" : [
        {
            "role": "user",
            "content": "hi"
        }
    ]
   }
 */

import java.util.ArrayList;
import java.util.List;

public class OpenAIRequest {
    private List<Message> messages;

    // Getters and Setters
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public static class Message {
        private String role = "user";
        private String content = "hi";

        public Message() { }

        public Message(String content) {
            this.content = content;
        }
        // Getters and Setters
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
