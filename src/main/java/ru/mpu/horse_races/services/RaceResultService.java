package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceResultDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;

public interface RaceResultService {
    RaceResultDto insert(CreateOrUpdateRaceResultDtoRq raceResult);

    RaceResultDto update(CreateOrUpdateRaceResultDtoRq raceResult);
}
