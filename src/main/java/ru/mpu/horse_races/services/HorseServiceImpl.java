package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateHorseDtoRq;
import ru.mpu.horse_races.domain.dtos.HorseDto;
import ru.mpu.horse_races.repositories.HorseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl implements HorseService {
    private final HorseRepository horseRepository;

    @Override
    public HorseDto insert(CreateOrUpdateHorseDtoRq horse) {
        return null;
    }

    @Override
    public HorseDto update(CreateOrUpdateHorseDtoRq horse) {
        return null;
    }

    @Override
    public List<HorseDto> findAll() {
        return null;
    }
}
