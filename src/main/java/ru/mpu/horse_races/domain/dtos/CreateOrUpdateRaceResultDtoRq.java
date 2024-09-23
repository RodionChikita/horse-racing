package ru.mpu.horse_races.domain.dtos;

import java.sql.Time;

public class CreateOrUpdateRaceResultDtoRq {
    private Long id;

    private String place;

    private Time horceTime;

    private Long raceId;

    private Long jockeyId;

    private Long horseId;
}
