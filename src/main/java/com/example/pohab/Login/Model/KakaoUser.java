package com.example.pohab.Login.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    private Long userCode;

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column(name = "kakao_nickname")
    private String userName;

    @Column(name = "kakao_email")
    private String kakaoEmail;

    @Column(name = "user_role")
    private String userRole;

    @Builder
    public KakaoUser(Long kakaoId, String userName,
                     String kakaoEmail, String userRole) {
        this.kakaoId = kakaoId;
        this.userName = userName;
        this.kakaoEmail = kakaoEmail;
        this.userRole = userRole;
    }

}
