package com.byq.aispringcloudalibaba.service;

import org.springframework.ai.image.ImageResponse;

public interface ImagesService {

    ImageResponse genImg(String imgPrompt);

}
