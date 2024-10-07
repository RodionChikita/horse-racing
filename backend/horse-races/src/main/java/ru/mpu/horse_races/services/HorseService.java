package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateHorseDtoRq;
import ru.mpu.horse_races.domain.dtos.HorseDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;

import java.util.List;

public interface HorseService {
    HorseDto insert(CreateOrUpdateHorseDtoRq horse);

    HorseDto update(CreateOrUpdateHorseDtoRq horse);

    List<HorseDto> findAll();

    List<RaceResultDto> findAllHorseRaceResults(Long id);

    HorseDto findById(Long id);

    void deleteById(Long id);
}
