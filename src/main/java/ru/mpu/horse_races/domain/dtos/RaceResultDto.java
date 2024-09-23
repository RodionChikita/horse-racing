package ru.mpu.horse_races.domain.dtos;

import java.sql.Time;

public class RaceResultDto {
    private Long id;

    private String place;

    private Time horceTime;

    private RaceDto race;

    private JockeyDto jockey;

    private HorseDto horse;
}
