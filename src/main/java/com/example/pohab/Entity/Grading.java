package com.example.pohab.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Grading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "standard_id")
    private GradingStandard grading_standard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grading_id")
    private GradingStatus grading_status;

    @Column(nullable = false)
    private int score;
}