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

    //빌더
    @Builder
    public Party(String name, String category) {
        this.name = name;
        this.category = category;
    }
}