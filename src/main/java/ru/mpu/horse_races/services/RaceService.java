package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceDto;

import java.util.List;

public interface RaceService {
    void insert(CreateOrUpdateRaceDtoRq race);

    void update(CreateOrUpdateRaceDtoRq race);

    List<RaceDto> findAll();
}
