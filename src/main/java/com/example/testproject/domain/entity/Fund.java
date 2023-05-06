package com.example.testproject.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FUND")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @Getter
    @Column(name = "MONTH", nullable = false)
    private Integer month;

    @Getter
    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @Getter
    @ManyToOne
    @JoinColumn(name = "INSTITUTE", nullable = false)
    private Institute institute;

}
