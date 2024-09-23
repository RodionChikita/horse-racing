package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceResultDtoRq;

public interface RaceResultService {
    void insert(CreateOrUpdateRaceResultDtoRq raceResult);

    void update(CreateOrUpdateRaceResultDtoRq raceResult);
}
