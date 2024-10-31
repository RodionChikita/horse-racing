package ru.mpu.horse_races.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    MALE("MALE"), FEMALE("FEMALE");
    private final String name;
}
