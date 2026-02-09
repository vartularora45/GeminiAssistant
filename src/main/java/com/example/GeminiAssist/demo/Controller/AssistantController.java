package com.example.GeminiAssist.demo.Controller;

import com.example.GeminiAssist.demo.Model.ChatMessage;
import com.example.GeminiAssist.demo.Service.AssistantService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AssistantController {

    private final AssistantService assistantService;

    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @PostMapping("/assist")
    public ChatMessage create(@RequestBody ChatMessage prompt) {
        return assistantService.create(prompt);
    }


}
