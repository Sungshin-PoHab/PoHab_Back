package com.example.pohab.Login.Repository;

import com.example.pohab.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoUserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

}
