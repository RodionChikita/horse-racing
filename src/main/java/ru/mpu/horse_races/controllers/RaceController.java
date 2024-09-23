package ru.mpu.horse_races.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mpu.horse_races.services.RaceService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/race")
public class RaceController {
    private final RaceService raceService;
}
