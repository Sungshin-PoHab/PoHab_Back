package com.example.pohab.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {
    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(length = 27, nullable = false)
    private String group_id;

    @Column(length = 15, nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "party_id")
    private Party party;

    //빌더
    @Builder
    public Staff(User user, String group_id, String role) {
        this.user = user;
        this.group_id = group_id;
        this.role = role;
    }
}