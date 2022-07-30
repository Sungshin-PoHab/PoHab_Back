package com.example.pohab.Entity;

import com.example.pohab.Enum.IsPass;
import com.example.pohab.Enum.IsSubmit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "pass")
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_status_id")
    private ApplyStatus applyStatus;

    private IsSubmit is_submit;

    private IsPass is_pass;

    private int num; // 차수
}