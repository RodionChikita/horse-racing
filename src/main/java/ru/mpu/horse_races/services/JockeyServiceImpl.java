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
    public JockeyDto insert(CreateOrUpdateJockeyDtoRq jockey) {

        return null;
    }

    @Override
    public JockeyDto update(CreateOrUpdateJockeyDtoRq jockey) {

        return null;
    }

    @Override
    public List<JockeyDto> findAll() {
        return null;
    }
}
