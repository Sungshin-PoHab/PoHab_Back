package com.example.pohab.Entity;

import com.example.pohab.Enum.IsPass;
import com.example.pohab.Enum.IsSubmit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ApplyStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_id")
    private Step step;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime tmp_date;

    private LocalDateTime sub_date;

    @ColumnDefault("0")
    private IsSubmit is_submit;

    @ColumnDefault("0")
    private IsPass is_pass;

    @PrePersist
    public void prePersist() {
        this.tmp_date = this.tmp_date == null ? LocalDateTime.now() : this.tmp_date;
        this.is_submit = this.is_submit == null ? is_submit.temp : this.is_submit;
        this.is_pass = this.is_pass == null ? IsPass.waiting : this.is_pass;
    }
}