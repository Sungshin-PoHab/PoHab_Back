package com.example.pohab.Repository;

import com.example.pohab.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findAllByApplyStatus_id(Integer apply_id);
}
