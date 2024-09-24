package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateJockeyDtoRq;
import ru.mpu.horse_races.domain.dtos.JockeyDto;
import ru.mpu.horse_races.domain.entities.Jockey;
import ru.mpu.horse_races.domain.entities.Owner;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.mappers.MappersToDto;
import ru.mpu.horse_races.mappers.MappersToEntity;
import ru.mpu.horse_races.repositories.JockeyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JockeyServiceImpl implements JockeyService {
    private final JockeyRepository jockeyRepository;

    @Override
    public JockeyDto insert(CreateOrUpdateJockeyDtoRq jockey) {
        return MappersToDto.MAP_TO_JOCKEY_DTO_FUNCTION.apply(MappersToEntity.MAP_TO_JOCKEY_FUNCTION.apply(jockey));
    }

    @Override
    public JockeyDto update(CreateOrUpdateJockeyDtoRq jockey) {
        Jockey updatedJockey =jockeyRepository.findById(jockey.getId())
                .orElseThrow(() -> new NotFoundException("Jockey with id %d not found".formatted(jockey.getId())));
        updatedJockey.setAddress(jockey.getAddress());
        updatedJockey.setName(jockey.getName());
        updatedJockey.setAge(jockey.getAge());
        updatedJockey.setRating(jockey.getRating());
        return MappersToDto.MAP_TO_JOCKEY_DTO_FUNCTION.apply(jockeyRepository.save(updatedJockey));
    }

    @Override
    public List<JockeyDto> findAll() {
        return jockeyRepository.findAll().stream().map(MappersToDto.MAP_TO_JOCKEY_DTO_FUNCTION).collect(Collectors.toList());
    }

    @Override
    public JockeyDto findById(Long id) {
        return MappersToDto.MAP_TO_JOCKEY_DTO_FUNCTION.apply(jockeyRepository.findById(id).orElseThrow(() -> new NotFoundException("Jockey with id %d not found".formatted(id))));
    }
}
