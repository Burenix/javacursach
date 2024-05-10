package ru.edu.penzgtu.lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "presidents")
public class President {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 52)
    private String name;

    @Column(name = "years_old", nullable = false, length = 52)
    private Long yearsOld;

    @Column(name = "language", nullable = false, length = 52)
    private String language;

    @Column(name = "term_start", nullable = false, length = 52)
    private Long termStart;

    @Column(name = "date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @JsonIgnoreProperties("president")
    private Country country;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "law_id", referencedColumnName = "id")
    private Law law;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "party_id", referencedColumnName = "id")
    private Party party;
}
