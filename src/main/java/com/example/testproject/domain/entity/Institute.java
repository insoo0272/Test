package com.example.testproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INSTITUTE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Institute{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="NAME", nullable = false, length = 50)
    private String name;

    @Getter
    @Setter
    @Column(name="CODE", nullable = false, length = 50)
    private String code;

    @JsonIgnore
    @Setter
    @Getter
    @OneToMany(mappedBy = "institute")
    @Builder.Default
    private List<Fund> fundList = new ArrayList<>();

    public Institute(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
