package ru.mpu.horse_races.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceResultDto {
    private Long id;

    private String place;

    private Time horceTime;

    private Long raceId;

    private JockeyDto jockey;

    private HorseDto horse;
}
