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
public class CreateOrUpdateRaceResultDtoRq {
    private Long id;

    private String place;

    private Time horceTime;

    private Long raceId;

    private Long jockeyId;

    private Long horseId;
}
