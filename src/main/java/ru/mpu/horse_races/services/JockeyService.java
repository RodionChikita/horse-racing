package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateJockeyDtoRq;
import ru.mpu.horse_races.domain.dtos.JockeyDto;

import java.util.List;

public interface JockeyService {
    void insert(CreateOrUpdateJockeyDtoRq jockey);

    void update(CreateOrUpdateJockeyDtoRq jockey);

    List<JockeyDto> findAll();
}
