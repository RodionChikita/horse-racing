package ru.mpu.horse_races.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateRaceDtoRq {
    private Long id;

    private String name;

    private LocalDate raceDate;

    private LocalTime raceTime;

    private String location;
}
