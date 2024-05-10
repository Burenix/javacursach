package ru.edu.penzgtu.lab.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "parties")
public class Party {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 52)
    private String name;

    @Column(name = "date_signed", nullable = false, length = 52)
    private Long dateSigned;

    @Column(name = "description", nullable = false, length = 52)
    private String description;

    @Column(name = "ideology", nullable = false, length = 52)
    private String ideology;

    @Column(name = "date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "party", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<President> presidents = new ArrayList<>();
}
