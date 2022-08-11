package com.example.pohab.Login.Model;

import com.example.pohab.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String email;
    private String name;
    @JsonIgnore
    private String authority;

    public UserDetailsImpl(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.authority = "user";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(authority));
    }

    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getName() );
    }


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
