package com.example.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto {
    private String messageId;
    private String senderId;
    private String receiverId;
    private String subject;
    private String content;
    private String timeStamp;
}
