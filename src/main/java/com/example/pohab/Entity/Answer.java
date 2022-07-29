package com.example.pohab.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 추후 관계 설정 수정
    private Integer apply_id;

    @OneToOne(cascade = CascadeType.REMOVE, targetEntity = Question.class)
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;

    public Answer(Integer id, Integer apply_id, Question question, String answer) {
        this.id = id;
        this.apply_id = apply_id;
        this.question = question;
        this.answer = answer;
    }
}
