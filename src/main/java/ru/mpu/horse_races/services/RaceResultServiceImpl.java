package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceResultDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.domain.entities.RaceResult;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.mappers.MappersToDto;
import ru.mpu.horse_races.repositories.HorseRepository;
import ru.mpu.horse_races.repositories.JockeyRepository;
import ru.mpu.horse_races.repositories.RaceRepository;
import ru.mpu.horse_races.repositories.RaceResultRepository;

@Service
@RequiredArgsConstructor
public class RaceResultServiceImpl implements RaceResultService {
    private final RaceResultRepository raceResultRepository;

    private final RaceRepository raceRepository;

    private final HorseRepository horseRepository;

    private final JockeyRepository jockeyRepository;

    @Override
    @Transactional
    public RaceResultDto insert(CreateOrUpdateRaceResultDtoRq raceResult) {
        raceResult.setId(0L);
        var race = raceRepository.findById(raceResult.getRaceId()).orElseThrow(
                () -> new NotFoundException("Race with id %d not found".formatted(raceResult.getRaceId())));
        var horse = horseRepository.findById(raceResult.getHorseId()).orElseThrow(
                () -> new NotFoundException("Horse with id %d not found".formatted(raceResult.getHorseId())));
        var jockey = jockeyRepository.findById(raceResult.getJockeyId()).orElseThrow(
                () -> new NotFoundException("Jockey with id %d not found".formatted(raceResult.getJockeyId())));
        var raceResultInserted = new RaceResult(0L, raceResult.getPlace(),
                raceResult.getHorceTime(), race, jockey, horse);
        return MappersToDto.MAP_TO_RACE_RESULT_DTO_FUNCTION.apply(raceResultRepository.save(raceResultInserted));
    }

    @Override
    @Transactional
    public RaceResultDto update(CreateOrUpdateRaceResultDtoRq raceResult) {
        var race = raceRepository.findById(raceResult.getRaceId()).orElseThrow(
                () -> new NotFoundException("Race with id %d not found".formatted(raceResult.getRaceId())));
        var horse = horseRepository.findById(raceResult.getHorseId()).orElseThrow(
                () -> new NotFoundException("Horse with id %d not found".formatted(raceResult.getHorseId())));
        var jockey = jockeyRepository.findById(raceResult.getJockeyId()).orElseThrow(
                () -> new NotFoundException("Jockey with id %d not found".formatted(raceResult.getJockeyId())));
        var raceResultUpdated = raceResultRepository.findById(raceResult.getId()).orElseThrow(
                () -> new NotFoundException("Race result with id %d not found".formatted(raceResult.getId())));
        raceResultUpdated.setPlace(raceResultUpdated.getPlace());
        raceResultUpdated.setHorceTime(raceResultUpdated.getHorceTime());
        raceResultUpdated.setHorse(horse);
        raceResultUpdated.setJockey(jockey);
        return MappersToDto.MAP_TO_RACE_RESULT_DTO_FUNCTION.apply(raceResultRepository.save(raceResultUpdated));
    }

    @Override
    @Transactional(readOnly = true)
    public RaceResultDto findById(Long id) {
        return MappersToDto.MAP_TO_RACE_RESULT_DTO_FUNCTION.apply(raceResultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Race result with id %d not found".formatted(id))));
    }

    @Override
    public void deleteById(Long id) {
        raceResultRepository.deleteById(id);
    }
}
