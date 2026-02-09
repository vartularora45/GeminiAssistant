package com.example.GeminiAssist.demo.Service;

import com.example.GeminiAssist.demo.Model.ChatMessage;
import com.example.GeminiAssist.demo.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AssistantService {

    private final ChatRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${groq.api.key}")
    private String GROQ_API_KEY;

    private static final String GROQ_URL =
            "https://api.groq.com/openai/v1/responses";

    public AssistantService(ChatRepository repository) {
        this.repository = repository;
    }

    public ChatMessage create(ChatMessage assist) {

        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(GROQ_API_KEY);

        // body (Responses API format)
        Map<String, Object> body = Map.of(
                "model", "openai/gpt-oss-20b",
                "input", assist.getPrompt()
        );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

        Map response = restTemplate.postForObject(
                GROQ_URL,
                entity,
                Map.class
        );

        // 🔥 extract output_text
        String aiReply = extractOutputText(response);

        assist.setResponse(aiReply);
        return repository.save(assist);
    }

    // helper method
    private String extractOutputText(Map response) {

        List output = (List) response.get("output");
        Map first = (Map) output.get(0);
        List content = (List) first.get("content");
        Map textBlock = (Map) content.get(0);

        return textBlock.get("text").toString();
    }
}
