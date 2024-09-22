package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.repositories.RaceRepository;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
}
