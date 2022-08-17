package com.example.pohab.Service;

import com.example.pohab.DTO.ChatDto;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Chat;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Entity.User;
import com.example.pohab.Repository.ApplyStatusRepository;
import com.example.pohab.Repository.ChatRepository;
import com.example.pohab.Repository.StaffRepository;
import com.example.pohab.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ApplyStatusRepository applyStatusRepository;
    private final StaffRepository staffRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatDto createChat(ChatDto chatDto, Integer writerId) {
        Chat chat = new Chat();

        ApplyStatus applyStatus = this.applyStatusRepository.findById(chatDto.getApplyId()).orElse(null);
        User user = this.userRepository.findById(writerId).orElse(null);

        if (applyStatus == null || user == null) {
            System.out.println("error");
        } else {
            String party_id = this.applyStatusRepository.getPartyFromApplyStatus(chatDto.getApplyId());
            Staff staff = this.staffRepository.findByUser_idAndParty_id(writerId, party_id);
            chatDto.setName(user.getName());
            chatDto.setRole(staff.getRole());
            chat.setStaff(staff);
            chat.setApplyStatus(applyStatus);
            chat.setChat(chatDto.getChat());
            this.chatRepository.save(chat);
        }

        return chatDto;
    }
}
