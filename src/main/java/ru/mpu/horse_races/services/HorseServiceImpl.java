package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateHorseDtoRq;
import ru.mpu.horse_races.domain.dtos.HorseDto;
import ru.mpu.horse_races.repositories.HorseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl implements HorseService {
    private final HorseRepository horseRepository;

    @Override
    @Transactional
    public HorseDto insert(CreateOrUpdateHorseDtoRq horse) {
        return null;
    }

    @Override
    @Transactional
    public HorseDto update(CreateOrUpdateHorseDtoRq horse) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HorseDto> findAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public HorseDto findById(Long id) {
        return null;
    }
}
