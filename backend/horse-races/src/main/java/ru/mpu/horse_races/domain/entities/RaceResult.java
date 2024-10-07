package ru.mpu.horse_races.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @Column(name = "horse_time", nullable = false)
    private Time horceTime;

    @ManyToOne(targetEntity = Race.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Race race;

    @OneToOne(targetEntity = Jockey.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "jockey_id")
    private Jockey jockey;

    @OneToOne(targetEntity = Horse.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "horse_id")
    private Horse horse;
}
