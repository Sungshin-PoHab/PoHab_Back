package com.example.pohab.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
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

    public ApplyStatus(Integer id, Integer user_id, String group_id, String is_submit, String is_pass) {
        this.id = id;
        this.user_id = user_id;
        this.group_id = group_id;
        this.is_submit = is_submit;
        this.is_pass = is_pass;
    }
}
