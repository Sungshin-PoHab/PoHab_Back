package com.example.pohab.Login.Repository;

import com.example.pohab.Login.Model.KakaoUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {
    KakaoUser findByKakaoEmail(String kakaoEmail);
    KakaoUser findByUserCode(String userCode);

    // 이미 가입된 계정인지 찾기
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(" select k from KakaoUser k where k.kakaoEmail = :user_id")
    Optional<KakaoUser> findByEmail(@Param("user_id") String user_id);

}
