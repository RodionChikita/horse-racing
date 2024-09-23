package ru.mpu.horse_races.domain.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RaceDto {
    private Long id;

    private String name;

    private LocalDate raceDate;

    private LocalTime raceTime;

    private String location;

    private List<RaceResultDto> raceResults;
}
