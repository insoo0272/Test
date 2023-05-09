package com.example.testproject.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FUND")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @Column(name = "MONTH", nullable = false)
    private Integer month;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "INSTITUTE", nullable = false)
    private Institute institute;

}
