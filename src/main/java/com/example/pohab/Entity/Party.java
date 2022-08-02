package com.example.pohab.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

    @Column(length = 10, nullable = false)
    private String code;

    //빌더
    @Builder
    public Party(String name, String category, Integer personnel, String code) {
        this.name = name;
        this.category = category;
        this.personnel = personnel;
        this.code = code;
    }
}