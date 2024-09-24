package ru.mpu.horse_races.mappers;

import lombok.RequiredArgsConstructor;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateJockeyDtoRq;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateOwnerDtoRq;
import ru.mpu.horse_races.domain.entities.Jockey;
import ru.mpu.horse_races.domain.entities.Owner;

import java.util.function.Function;

@RequiredArgsConstructor
public class MappersToEntity {

    public static final Function<CreateOrUpdateOwnerDtoRq, Owner> MAP_TO_OWNER_FUNCTION =
            o -> new Owner(o.getId(), o.getName(), o.getAddress(), o.getPhoneNumber());

    public static final Function<CreateOrUpdateJockeyDtoRq, Jockey> MAP_TO_JOCKEY_FUNCTION =
            j -> new Jockey(j.getId(), j.getName(), j.getAddress(), j.getAge(), j.getRating());
}
