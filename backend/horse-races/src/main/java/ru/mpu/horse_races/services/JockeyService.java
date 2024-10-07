package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateJockeyDtoRq;
import ru.mpu.horse_races.domain.dtos.JockeyDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;

import java.util.List;

public interface JockeyService {
    JockeyDto insert(CreateOrUpdateJockeyDtoRq jockey);

    JockeyDto update(CreateOrUpdateJockeyDtoRq jockey);

    List<JockeyDto> findAll();

    List<RaceResultDto> findAllJockeyRaceResults(Long id);

    JockeyDto findById(Long id);

    void deleteById(Long id);
}
