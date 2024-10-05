package ru.mpu.horse_races.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceResultDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.services.RaceResultService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/race_result")
public class RaceResultController {
    private final RaceResultService raceResultService;

    @PostMapping
    public ResponseEntity<RaceResultDto> addRaceResult(@RequestBody CreateOrUpdateRaceResultDtoRq raceResult) {
        RaceResultDto newRaceResult = raceResultService.insert(raceResult);
        return new ResponseEntity<>(newRaceResult, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RaceResultDto> updateRaceResult(@RequestBody CreateOrUpdateRaceResultDtoRq updatedRaceResult) {
        RaceResultDto raceResult = raceResultService.update(updatedRaceResult);
        return new ResponseEntity<>(raceResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaceResult(@PathVariable Long id) {
        raceResultService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
