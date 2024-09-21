package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.repositories.JockeyRepository;

@Service
@RequiredArgsConstructor
public class JockeyServiceImpl implements JockeyService{
    private final JockeyRepository jockeyRepository;
}
