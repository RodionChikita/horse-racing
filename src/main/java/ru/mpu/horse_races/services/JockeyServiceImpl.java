package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateJockeyDtoRq;
import ru.mpu.horse_races.domain.dtos.JockeyDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.domain.entities.Jockey;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.mappers.MappersToDto;
import ru.mpu.horse_races.mappers.MappersToEntity;
import ru.mpu.horse_races.repositories.JockeyRepository;
import ru.mpu.horse_races.repositories.RaceResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JockeyServiceImpl implements JockeyService {
    private final JockeyRepository jockeyRepository;

    private final RaceResultRepository raceResultRepository;

    @Override
    @Transactional
    public JockeyDto insert(CreateOrUpdateJockeyDtoRq jockey) {
        jockey.setId(0L);
        return MappersToDto.MAP_TO_JOCKEY_DTO_FUNCTION.apply(MappersToEntity.MAP_TO_JOCKEY_FUNCTION.apply(jockey));
    }

    @Override
    @Transactional
    public JockeyDto update(CreateOrUpdateJockeyDtoRq jockey) {
        Jockey updatedJockey = jockeyRepository.findById(jockey.getId())
                .orElseThrow(() -> new NotFoundException("Jockey with id %d not found".formatted(jockey.getId())));
        updatedJockey.setAddress(jockey.getAddress());
        updatedJockey.setName(jockey.getName());
        updatedJockey.setAge(jockey.getAge());
        updatedJockey.setRating(jockey.getRating());
        return MappersToDto.MAP_TO_JOCKEY_DTO_FUNCTION.apply(jockeyRepository.save(updatedJockey));
    }

    @Override
    @Transactional(readOnly = true)
    public List<JockeyDto> findAll() {
        return jockeyRepository.findAll().stream().map(MappersToDto.MAP_TO_JOCKEY_DTO_FUNCTION)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RaceResultDto> findAllJockeyRaceResults(Long id) {
        return raceResultRepository.findByJockeyId(id).stream().map(MappersToDto.MAP_TO_RACE_RESULT_DTO_FUNCTION)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public JockeyDto findById(Long id) {
        return MappersToDto.MAP_TO_JOCKEY_DTO_FUNCTION.apply(jockeyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Jockey with id %d not found".formatted(id))));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jockeyRepository.deleteById(id);
    }
}
