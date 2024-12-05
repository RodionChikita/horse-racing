package ru.mpu.horse_races.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.services.RaceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/race")
@PreAuthorize("hasRole('USER')")
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
