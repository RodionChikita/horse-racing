package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceResultDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.repositories.RaceResultRepository;

@Service
@RequiredArgsConstructor
public class RaceResultServiceImpl implements RaceResultService {
    private final RaceResultRepository raceResultRepository;

    @Override
    public RaceResultDto insert(CreateOrUpdateRaceResultDtoRq raceResult) {
        return null;
    }

    @Override
    public RaceResultDto update(CreateOrUpdateRaceResultDtoRq raceResult) {

        return null;
    }
}
