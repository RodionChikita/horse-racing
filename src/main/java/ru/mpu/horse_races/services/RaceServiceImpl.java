package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceDto;
import ru.mpu.horse_races.repositories.RaceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;

    @Override
    public RaceDto insert(CreateOrUpdateRaceDtoRq race) {

        return null;
    }

    @Override
    public RaceDto update(CreateOrUpdateRaceDtoRq race) {

        return null;
    }

    @Override
    public List<RaceDto> findAll() {
        return null;
    }
}
