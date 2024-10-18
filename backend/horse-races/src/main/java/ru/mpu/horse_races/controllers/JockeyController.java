package ru.mpu.horse_races.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateJockeyDtoRq;
import ru.mpu.horse_races.domain.dtos.JockeyDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.services.JockeyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jockey")
public class JockeyController {
    private final JockeyService jockeyService;

    @GetMapping
    public List<JockeyDto> getAllJockeys() {
        return jockeyService.findAll();
    }

    @GetMapping("/{id}/race_results")
    public List<RaceResultDto> getAllJockeyRaceResults(@PathVariable Long id) {
        return jockeyService.findAllJockeyRaceResults(id);
    }

    @PostMapping
    public ResponseEntity<JockeyDto> addJockey(@RequestBody CreateOrUpdateJockeyDtoRq jockey) {
        JockeyDto newJockey = jockeyService.insert(jockey);
        return new ResponseEntity<>(newJockey, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<JockeyDto> updateJockey(@RequestBody CreateOrUpdateJockeyDtoRq updatedJockey) {
        JockeyDto jockey = jockeyService.update(updatedJockey);
        return new ResponseEntity<>(jockey, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJockey(@PathVariable Long id) {
        jockeyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
