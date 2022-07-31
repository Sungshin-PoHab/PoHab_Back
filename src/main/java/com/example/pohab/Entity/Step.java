package com.example.pohab.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.Group;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "pass")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    private int step;

    private LocalDateTime end_date;
}