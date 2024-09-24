package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateHorseDtoRq;
import ru.mpu.horse_races.domain.dtos.HorseDto;

import java.util.List;

public interface HorseService {
    HorseDto insert(CreateOrUpdateHorseDtoRq horse);

    HorseDto update(CreateOrUpdateHorseDtoRq horse);

    List<HorseDto> findAll();
}
