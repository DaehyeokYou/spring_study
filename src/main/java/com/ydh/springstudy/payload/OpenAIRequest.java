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

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenAIRequest {
    private List<Message> messages;

    @Getter
    @Setter
    public static class Message {
        private String role;
        private String content;
    }
}
