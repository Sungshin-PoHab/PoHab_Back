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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(length = 25, nullable = false)
    private String name;

    @Column(length = 25, nullable = false)
    private String category;

    private Integer personnel;

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