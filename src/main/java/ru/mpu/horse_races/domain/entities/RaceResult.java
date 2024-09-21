package ru.mpu.horse_races.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "race_results")
public class RaceResult {
    @Id
    private Long id;
    @Column(name = "place", nullable = false)
    private String place;
    @Column(name = "time", nullable = false)
    private Time time;
    @ManyToOne(targetEntity = Race.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "race_id")
    private Race race;
    @OneToOne(targetEntity = Jockey.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "jockey_id")
    private Jockey jockey;
    @OneToOne(targetEntity = Horse.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "horse_id")
    private Horse horse;
}
