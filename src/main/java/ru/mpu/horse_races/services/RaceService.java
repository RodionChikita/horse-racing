package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceDto;

import java.util.List;

public interface RaceService {
    RaceDto insert(CreateOrUpdateRaceDtoRq race);

    RaceDto update(CreateOrUpdateRaceDtoRq race);

    List<RaceDto> findAll();

    RaceDto findById(Long id);

    void deleteById(Long id);
}
