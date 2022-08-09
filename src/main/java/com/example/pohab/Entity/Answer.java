package com.example.pohab.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 추후 관계 설정 수정
    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = ApplyStatus.class)
    @JoinColumn(name = "apply_id")
    private ApplyStatus applyStatus;

    @OneToOne(cascade = CascadeType.REMOVE, targetEntity = Question.class)
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;
}
