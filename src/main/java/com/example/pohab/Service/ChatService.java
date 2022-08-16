package com.example.pohab.Service;

import com.example.pohab.DTO.ChatDto;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Chat;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Repository.ApplyStatusRepository;
import com.example.pohab.Repository.ChatRepository;
import com.example.pohab.Repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ApplyStatusRepository applyStatusRepository;
    private final StaffRepository staffRepository;
    private final ChatRepository chatRepository;

    public void createChat(ChatDto chatDto, Integer writerId) {
        Chat chat = new Chat();

        ApplyStatus applyStatus = this.applyStatusRepository.findById(chatDto.getApplyId()).orElse(null);

        if (applyStatus == null) {
            System.out.println("error");
        } else {
            String party_id = this.applyStatusRepository.getPartyFromApplyStatus(chatDto.getApplyId());
            Staff staff = this.staffRepository.findByUser_idAndParty_id(writerId, party_id);
            chat.setStaff(staff);
            chat.setApplyStatus(applyStatus);
            chat.setChat(chatDto.getChat());
            this.chatRepository.save(chat);
        }
    }
}
