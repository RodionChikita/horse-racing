package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateOwnerDtoRq;
import ru.mpu.horse_races.domain.dtos.OwnerDto;
import ru.mpu.horse_races.repositories.OwnerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public void insert(CreateOrUpdateOwnerDtoRq owner) {

    }

    @Override
    public void update(CreateOrUpdateOwnerDtoRq owner) {

    }

    @Override
    public List<OwnerDto> findAll() {
        return null;
    }
}
