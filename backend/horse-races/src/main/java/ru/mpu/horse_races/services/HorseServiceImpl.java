package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateHorseDtoRq;
import ru.mpu.horse_races.domain.dtos.HorseDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.domain.entities.Horse;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.mappers.MappersToDto;
import ru.mpu.horse_races.repositories.HorseRepository;
import ru.mpu.horse_races.repositories.OwnerRepository;
import ru.mpu.horse_races.repositories.RaceResultRepository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl implements HorseService {
    private final HorseRepository horseRepository;

    private final OwnerRepository ownerRepository;

    private final RaceResultRepository raceResultRepository;

    private static final Logger logger = LoggerFactory.getLogger(HorseServiceImpl.class);

    @Override
    @Transactional
    public HorseDto insert(CreateOrUpdateHorseDtoRq horse) {
        logger.info("Inserting horse with data: {}", horse);
        logger.info("Inserting horse with data: {}", horse.getOwnerId());
        logger.info("Inserting horse with data: {}", horse.getGenderEnum());
        var owner = ownerRepository.findById(horse.getOwnerId())
                .orElseThrow(() -> new NotFoundException("Owner with id %d not found".formatted(horse.getOwnerId())));
        var horseInserted = new Horse(0L, horse.getNickname(),horse.getGenderEnum(), horse.getAge(), owner);
        return MappersToDto.MAP_TO_HORSE_DTO_FUNCTION.apply(horseRepository.save(horseInserted.getNickname(), horseInserted.getGenderEnum().name(), horseInserted.getAge(), horseInserted.getOwner().getId()));
    }

    @Override
    @Transactional
    public HorseDto update(CreateOrUpdateHorseDtoRq horse) {
        var owner = ownerRepository.findById(horse.getOwnerId())
                .orElseThrow(() -> new NotFoundException("Owner with id %d not found".formatted(horse.getOwnerId())));
        var horseUpdated = horseRepository.findById(horse.getId())
                .orElseThrow(() -> new NotFoundException("Horse with id %d not found".formatted(horse.getId())));
        horseUpdated.setAge(horse.getAge());
        horseUpdated.setOwner(owner);
        horseUpdated.setNickname(horse.getNickname());
        horseUpdated.setGenderEnum(horse.getGenderEnum());
        return MappersToDto.MAP_TO_HORSE_DTO_FUNCTION.apply(horseRepository.save(horseUpdated.getNickname(), horseUpdated.getGenderEnum().name(), horseUpdated.getAge(), horseUpdated.getOwner().getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HorseDto> findAll() {
            return horseRepository.findAll().stream().map(MappersToDto.MAP_TO_HORSE_DTO_FUNCTION)
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RaceResultDto> findAllHorseRaceResults(Long id) {
        return raceResultRepository.findByHorseId(id).stream().map(MappersToDto.MAP_TO_RACE_RESULT_DTO_FUNCTION)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public HorseDto findById(Long id) {
        return MappersToDto.MAP_TO_HORSE_DTO_FUNCTION.apply(horseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Horse with id %d not found".formatted(id))));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        horseRepository.deleteById(id);
    }
}
