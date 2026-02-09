package com.example.GeminiAssist.demo.Service;

import com.example.GeminiAssist.demo.Model.ChatMessage;
import com.example.GeminiAssist.demo.Repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class AssistantService {
    private  final ChatRepository repository;

    public AssistantService(ChatRepository repository) {
        this.repository = repository;
    }
    public  ChatMessage create(ChatMessage assist){
        return repository.save(assist);
    }

}
