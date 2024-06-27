package com.byq.aispringcloudalibaba.service.impl;

import com.byq.aispringcloudalibaba.service.ChatService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.StreamingChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    // 聊天客户端
    private final ChatClient chatClient;
    // stream 流式客户端
    private final StreamingChatClient streamingChatClient;

    @Autowired
    public ChatServiceImpl(ChatClient chatClient, StreamingChatClient streamingChatClient) {

        this.chatClient = chatClient;
        this.streamingChatClient = streamingChatClient;
    }

    @Override
    public String normalCompletion(String message) {

        Prompt prompt = new Prompt(new UserMessage(message));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

    @Override
    public Map<String, String> streamCompletion(String message) {

        StringBuilder fullContent = new StringBuilder();

        streamingChatClient.stream(new Prompt(message))
                .flatMap(chatResponse -> Flux.fromIterable(chatResponse.getResults()))
                .map(content -> content.getOutput().getContent())
                .doOnNext(fullContent::append)
                .last()
                .map(lastContent -> Map.of(message, fullContent.toString()))
                .block();
        return Map.of(message, fullContent.toString());
    }

}

