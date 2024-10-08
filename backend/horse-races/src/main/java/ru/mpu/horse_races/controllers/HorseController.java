package ru.mpu.horse_races.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateHorseDtoRq;
import ru.mpu.horse_races.domain.dtos.HorseDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.services.HorseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/horse")
@CrossOrigin(origins = "http://0.0.0.0:4200", maxAge = 3600)
public class HorseController {
    private final HorseService horseService;

    @GetMapping
    public List<HorseDto> getAllHorses() {
        return horseService.findAll();
    }

    @GetMapping("/{id}/race_results")
    public List<RaceResultDto> getAllHorseRaceResults(@PathVariable Long id) {
        return horseService.findAllHorseRaceResults(id);
    }

    @PostMapping
    public ResponseEntity<HorseDto> addHorse(@RequestBody CreateOrUpdateHorseDtoRq horse) {
        HorseDto newHorse = horseService.insert(horse);
        return new ResponseEntity<>(newHorse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HorseDto> updateHorse(@RequestBody CreateOrUpdateHorseDtoRq updatedHorse) {
        HorseDto horse = horseService.update(updatedHorse);
        return new ResponseEntity<>(horse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorse(@PathVariable Long id) {
        horseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}