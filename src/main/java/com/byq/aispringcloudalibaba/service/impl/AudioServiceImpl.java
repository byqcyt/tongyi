package com.byq.aispringcloudalibaba.service.impl;

import com.alibaba.cloud.ai.tongyi.audio.api.SpeechClient;
import com.alibaba.dashscope.audio.tts.SpeechSynthesisAudioFormat;
import com.byq.aispringcloudalibaba.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AudioServiceImpl implements AudioService {

    private final SpeechClient speechClient;

    @Autowired
    public AudioServiceImpl(SpeechClient client) {

        this.speechClient = client;
    }

    @Override
    public String genAudio(String text) {

        var resWAV = speechClient.call(text);
        return save(resWAV, SpeechSynthesisAudioFormat.WAV.getValue());
    }

    // 辅助方法，用于将模型的响应保存到本地.
    private String save(ByteBuffer audio, String type) {

        String currentPath = System.getProperty("user.dir");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-HH-mm-ss");
        String fileName = currentPath + File.separator + now.format(formatter) + "." + type;
        File file = new File(fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(audio.array());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return fileName;
    }

}


