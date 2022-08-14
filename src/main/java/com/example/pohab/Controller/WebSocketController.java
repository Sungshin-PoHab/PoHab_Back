package com.example.pohab.Controller;

import com.example.pohab.DTO.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(ChatDto chatDto) {
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatDto.getApplyId(), chatDto);
    }
}
