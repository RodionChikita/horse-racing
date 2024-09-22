package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.repositories.RaceResultRepository;

@Service
@RequiredArgsConstructor
public class RaceResultServiceImpl implements RaceResultService {
    private final RaceResultRepository raceResultRepository;
}
