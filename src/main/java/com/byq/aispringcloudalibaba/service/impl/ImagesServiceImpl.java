package com.byq.aispringcloudalibaba.service.impl;

import com.byq.aispringcloudalibaba.service.ImagesService;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagesServiceImpl implements ImagesService {

    // 图像生成客户端
    private final ImageClient imageClient;

    @Autowired
    public ImagesServiceImpl(ImageClient client) {

        this.imageClient = client;
    }

    @Override
    public ImageResponse genImg(String imgPrompt) {

        var prompt = new ImagePrompt(imgPrompt);
        return imageClient.call(prompt);
    }

}

