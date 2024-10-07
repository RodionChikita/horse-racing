package ru.mpu.horse_races.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JockeyDto {
    private Long id;

    private String name;

    private String address;

    private byte age;

    private int rating;
}
