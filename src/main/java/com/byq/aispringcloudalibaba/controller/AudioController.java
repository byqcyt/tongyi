package com.byq.aispringcloudalibaba.controller;

import com.byq.aispringcloudalibaba.service.AudioService;
import com.byq.aispringcloudalibaba.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AudioController {

    @Resource
    private AudioService audioService;

    @GetMapping("/audio")
    public String genAudio(@RequestParam(value = "prompt",
            defaultValue = "你好，Spring Cloud Alibaba AI 框架！") String prompt) {

        return audioService.genAudio(prompt);
    }

}
