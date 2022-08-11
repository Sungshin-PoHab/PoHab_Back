package com.example.pohab.Login.Service;

import com.example.pohab.Entity.User;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Login.Repository.KakaoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    KakaoUserRepository kakaoUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = kakaoUserRepository.findUserByEmail(email);

        return UserDetailsImpl.build(user);
    }
}
