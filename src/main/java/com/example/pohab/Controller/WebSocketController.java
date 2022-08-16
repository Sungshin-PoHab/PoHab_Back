package com.example.pohab.Controller;

import com.example.pohab.DTO.ChatDto;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Chat;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Repository.ApplyStatusRepository;
import com.example.pohab.Repository.ChatRepository;
import com.example.pohab.Repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final StaffRepository staffRepository;
    private final ChatRepository chatRepository;
    private final ApplyStatusRepository applyStatusRepository;
    private HashMap<String, Integer> sessions = new HashMap<>();

    @GetMapping("/chat/{apply_id}")
    public List<Chat> getChatByApply(@PathVariable("apply_id") Integer apply_id) {
        return this.chatRepository.findAllByApplyStatus_id(apply_id);
    }

    @MessageMapping("/chat")
    public void sendMessage(ChatDto chatDto, SimpMessageHeaderAccessor accessor) {
        System.out.println(accessor.getSessionId());
        Integer writerId = sessions.get(accessor.getSessionId());
        chatDto.setWriterId(writerId);

        Chat chat = new Chat();
        Staff staff = this.staffRepository.findStaffByUser_id(writerId);
        ApplyStatus applyStatus = this.applyStatusRepository.findById(chatDto.getApplyId()).orElse(null);

        if (applyStatus == null) {
            System.out.println("error");
        } else {
            chat.setStaff(staff);
            chat.setApplyStatus(applyStatus);
            chat.setChat(chatDto.getChat());
            this.chatRepository.save(chat);
        }

        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatDto.getApplyId(), chatDto);
    }

    @EventListener(SessionConnectEvent.class)
    public void onConnect(SessionConnectEvent event){
        String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
        String userId = event.getMessage().getHeaders().get("nativeHeaders").toString().split("User=\\[")[1].split("]")[0];

        sessions.put(sessionId, Integer.valueOf(userId));
    }

    @EventListener(SessionDisconnectEvent.class)
    public void onDisconnect(SessionDisconnectEvent event) {
        sessions.remove(event.getSessionId());
    }

}
