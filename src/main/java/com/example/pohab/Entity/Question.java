package com.example.pohab.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String group_id;

    private String question;

    public Question(Integer id, String group_id, String question) {
        this.id = id;
        this.group_id = group_id;
        this.question = question;
    }
}
