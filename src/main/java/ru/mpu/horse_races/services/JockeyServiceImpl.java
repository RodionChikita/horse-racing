package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateJockeyDtoRq;
import ru.mpu.horse_races.domain.dtos.JockeyDto;
import ru.mpu.horse_races.repositories.JockeyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JockeyServiceImpl implements JockeyService {
    private final JockeyRepository jockeyRepository;

    @Override
    public void insert(CreateOrUpdateJockeyDtoRq jockey) {

    }

    @Override
    public void update(CreateOrUpdateJockeyDtoRq jockey) {

    }

    @Override
    public List<JockeyDto> findAll() {
        return null;
    }
}
