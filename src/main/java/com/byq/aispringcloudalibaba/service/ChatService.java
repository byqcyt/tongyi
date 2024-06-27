package com.byq.aispringcloudalibaba.service;

import java.util.Map;

public interface ChatService {

    String normalCompletion(String message);

    Map<String, String> streamCompletion(String message);

}
