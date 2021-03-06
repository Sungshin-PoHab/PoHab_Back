package com.example.pohab.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "party")
public class Party {
    //필드
    @Id
    @Column(unique = true, length = 25, nullable = false)
    private String id;

    @Column(length = 25, nullable = false)
    private String name;

    @Column(length = 25, nullable = false)
    private String category;

    @Column(length = 25, nullable = false)
    private Integer personnel; // 모집 인원

    //빌더
    @Builder
    public Party(String name, String category) {
        this.name = name;
        this.category = category;
    }
}