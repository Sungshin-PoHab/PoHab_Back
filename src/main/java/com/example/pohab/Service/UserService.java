package com.example.pohab.Service;

import com.example.pohab.Entity.User;
import com.example.pohab.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /** 사용자 저장 */
    public void save(User user) {
        userRepository.save(user);
    }

}
