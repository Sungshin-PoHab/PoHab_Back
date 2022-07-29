package com.example.pohab.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ApplyStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 추후 관계 수정 필요
    private Integer user_id;

    // 추후 관계 수정 필요
    private String group_id;

    private String is_submit;

    private String is_pass;
}
