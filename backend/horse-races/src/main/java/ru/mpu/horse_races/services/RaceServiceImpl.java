package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.domain.entities.Race;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.mappers.MappersToDto;
import ru.mpu.horse_races.repositories.RaceRepository;
import ru.mpu.horse_races.repositories.RaceResultRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;

    private final RaceResultRepository raceResultRepository;

    @Override
    @Transactional
    public RaceDto insert(CreateOrUpdateRaceDtoRq race) {
        race.setId(0L);
        var raceInserted = new Race(0L, race.getName(), race.getRaceDate(),
                race.getRaceTime(), race.getLocation(), new ArrayList<>());
        return MappersToDto.MAP_TO_RACE_DTO_FUNCTION.apply(raceRepository.save(raceInserted));
    }

    @Override
    @Transactional
    public RaceDto update(CreateOrUpdateRaceDtoRq race) {
        var raceUpdated = raceRepository.findById(race.getId()).orElseThrow(
                () -> new NotFoundException("Race with id %d not found".formatted(race.getId())));
        raceUpdated.setLocation(race.getLocation());
        raceUpdated.setName(race.getName());
        raceUpdated.setRaceDate(race.getRaceDate());
        raceUpdated.setRaceTime(race.getRaceTime());
        return MappersToDto.MAP_TO_RACE_DTO_FUNCTION.apply(raceRepository.save(raceUpdated));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RaceDto> findAll() {
        return raceRepository.findAll().stream().map(MappersToDto.MAP_TO_RACE_DTO_FUNCTION)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RaceDto findById(Long id) {
        return MappersToDto.MAP_TO_RACE_DTO_FUNCTION.apply(raceRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Race with id %d not found".formatted(id))));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        raceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RaceResultDto> findAllRaceRaceResults(Long id) {
        return raceResultRepository.findByRaceId(id).stream().map(MappersToDto.MAP_TO_RACE_RESULT_DTO_FUNCTION)
                .collect(Collectors.toList());
    }
}
