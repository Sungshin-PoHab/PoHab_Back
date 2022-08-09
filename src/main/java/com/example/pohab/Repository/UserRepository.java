package com.example.pohab.Repository;

import com.example.pohab.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    /** 부서별 & 단계별 채점 현황 */
    @Query("select user from User user where user.email = :email")
    User findUserByEmail(@Param("email") String email);

}
