package ru.mpu.horse_races.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mpu.horse_races.domain.dtos.HorseDto;
import ru.mpu.horse_races.services.HorseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/horse")
public class HorseController {
    private final HorseService horseService;
    @GetMapping
    public List<HorseDto> getAllHorses() {
        return horseService.findAll();
    }
}
