package ru.mpu.horse_races.domain.dtos;

import ru.mpu.horse_races.domain.entities.GenderEnum;

public class CreateOrUpdateHorseDtoRq {
    private Long id;

    private String nickname;

    private GenderEnum genreEnum;

    private byte age;

    private Long ownerId;
}
