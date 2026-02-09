package com.example.GeminiAssist.demo.Repository;

import com.example.GeminiAssist.demo.Model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository
extends MongoRepository<ChatMessage,String> {


}
