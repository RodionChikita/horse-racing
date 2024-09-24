package ru.mpu.horse_races.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateOwnerDtoRq;
import ru.mpu.horse_races.domain.dtos.OwnerDto;
import ru.mpu.horse_races.domain.entities.Owner;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.mappers.MappersToDto;
import ru.mpu.horse_races.mappers.MappersToEntity;
import ru.mpu.horse_races.repositories.OwnerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    @Transactional
    public OwnerDto insert(CreateOrUpdateOwnerDtoRq owner) {
        return MappersToDto.MAP_TO_OWNER_DTO_FUNCTION.apply(ownerRepository
                .save(MappersToEntity.MAP_TO_OWNER_FUNCTION.apply(owner)));
    }

    @Override
    @Transactional
    public OwnerDto update(CreateOrUpdateOwnerDtoRq owner) {
        Owner updatedOwner = ownerRepository.findById(owner.getId())
                .orElseThrow(() -> new NotFoundException("Owner with id %d not found".formatted(owner.getId())));
        updatedOwner.setAddress(owner.getAddress());
        updatedOwner.setName(owner.getName());
        updatedOwner.setPhoneNumber(owner.getPhoneNumber());
        return MappersToDto.MAP_TO_OWNER_DTO_FUNCTION.apply(ownerRepository.save(updatedOwner));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OwnerDto> findAll() {
        return ownerRepository.findAll().stream().map(MappersToDto.MAP_TO_OWNER_DTO_FUNCTION)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OwnerDto findById(Long id) {
        return MappersToDto.MAP_TO_OWNER_DTO_FUNCTION.apply(ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Owner with id %d not found".formatted(id))));
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
