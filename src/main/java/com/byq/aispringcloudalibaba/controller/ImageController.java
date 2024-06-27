package com.byq.aispringcloudalibaba.controller;

import com.byq.aispringcloudalibaba.service.ImagesService;
import jakarta.annotation.Resource;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Resource
    private ImagesService imagesService;

    @GetMapping("/img")
    public ImageResponse genImg(
            @RequestParam(value = "prompt", defaultValue = "Painting a picture of blue water and blue sky.") String imgPrompt
    ) {

        return imagesService.genImg(imgPrompt);
    }


}
