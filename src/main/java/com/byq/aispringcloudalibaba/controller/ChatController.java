package com.byq.aispringcloudalibaba.controller;

import com.byq.aispringcloudalibaba.service.ChatService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private ChatService chatService;

    @GetMapping("/example")
    public String completion(
            @RequestParam(value = "message", defaultValue = "Tell me a joke")
            String message
    ) {

        return chatService.normalCompletion(message);
    }

    @GetMapping("/stream")
    public Map<String, String> streamCompletion(
            @RequestParam(value = "message", defaultValue = "请告诉我西红柿炖牛腩怎么做？")
            String message
    ) {

        return chatService.streamCompletion(message);
    }


}
