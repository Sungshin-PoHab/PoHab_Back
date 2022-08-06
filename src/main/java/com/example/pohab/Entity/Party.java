package com.example.pohab.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "party")
public class Party {
    //필드
    @Id
    @Column(unique = true, length = 50, nullable = false)
    private String id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 25, nullable = false)
    private String category;

    @Column(length = 10, nullable = false)
    private String code;
    
    //빌더
    @Builder
    public Party(String name, String category, String code) {
        this.name = name;
        this.category = category;
        this.code = code;
    }
}