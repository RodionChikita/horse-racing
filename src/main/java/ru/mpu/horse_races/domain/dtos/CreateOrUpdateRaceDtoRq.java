package ru.mpu.horse_races.domain.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateOrUpdateRaceDtoRq {
    private Long id;

    private String name;

    private LocalDate raceDate;

    private LocalTime raceTime;

    private String location;
}
