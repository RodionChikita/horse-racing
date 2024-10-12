package ru.mpu.horse_races.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.services.RaceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/race")
@CrossOrigin(origins = "http://192.168.64.10:4200", maxAge = 3600)
public class RaceController {
    private final RaceService raceService;

    @GetMapping
    public List<RaceDto> getAllRaces() {
        return raceService.findAll();
    }

    @GetMapping("/{id}/race_results")
    public List<RaceResultDto> getAllRaceRaceResults(@PathVariable Long id) {
        return raceService.findAllRaceRaceResults(id);
    }

    @PostMapping
    public ResponseEntity<RaceDto> addRace(@RequestBody CreateOrUpdateRaceDtoRq race) {
        RaceDto newRace = raceService.insert(race);
        return new ResponseEntity<>(newRace, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RaceDto> updateRace(@RequestBody CreateOrUpdateRaceDtoRq updatedRace) {
        RaceDto race = raceService.update(updatedRace);
        return new ResponseEntity<>(race, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        raceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
