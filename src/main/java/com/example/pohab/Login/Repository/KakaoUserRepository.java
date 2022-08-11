package com.example.pohab.Login.Repository;

import com.example.pohab.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoUserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

}
