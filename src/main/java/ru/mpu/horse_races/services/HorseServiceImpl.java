package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.repositories.HorseRepository;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl implements HorseService{
    private final HorseRepository horseRepository;
}
