package ru.mpu.horse_races.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mpu.horse_races.domain.entities.GenderEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateHorseDtoRq {
    private Long id;

    private String nickname;

    private GenderEnum genreEnum;

    private byte age;

    private Long ownerId;
}
