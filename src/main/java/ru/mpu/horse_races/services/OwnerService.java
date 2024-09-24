package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.dtos.CreateOrUpdateOwnerDtoRq;
import ru.mpu.horse_races.domain.dtos.OwnerDto;

import java.util.List;

public interface OwnerService {
    OwnerDto insert(CreateOrUpdateOwnerDtoRq owner);

    OwnerDto update(CreateOrUpdateOwnerDtoRq owner);

    List<OwnerDto> findAll();
}
