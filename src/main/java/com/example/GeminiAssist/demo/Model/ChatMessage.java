package com.example.GeminiAssist.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "chats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChatMessage {

    @Id
    private String id;

    private String req;
    private String res;


}
