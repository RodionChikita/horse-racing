package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateHorseDtoRq;
import ru.mpu.horse_races.domain.dtos.HorseDto;

import java.util.List;

public interface HorseService {
    void insert(CreateOrUpdateHorseDtoRq horse);

    void update(CreateOrUpdateHorseDtoRq horse);

    List<HorseDto> findAll();
}
