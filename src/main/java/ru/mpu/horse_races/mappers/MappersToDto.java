package ru.mpu.horse_races.mappers;

import ru.mpu.horse_races.domain.dtos.HorseDto;
import ru.mpu.horse_races.domain.dtos.JockeyDto;
import ru.mpu.horse_races.domain.dtos.OwnerDto;
import ru.mpu.horse_races.domain.dtos.RaceDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.domain.entities.Horse;
import ru.mpu.horse_races.domain.entities.Jockey;
import ru.mpu.horse_races.domain.entities.Owner;
import ru.mpu.horse_races.domain.entities.Race;
import ru.mpu.horse_races.domain.entities.RaceResult;

import java.util.function.Function;
import java.util.stream.Collectors;

public class MappersToDto {
    public static final Function<Owner, OwnerDto> MAP_TO_OWNER_DTO_FUNCTION =
            o -> new OwnerDto(o.getId(), o.getName(), o.getAddress(), o.getPhoneNumber());

    public static final Function<Horse, HorseDto> MAP_TO_HORSE_DTO_FUNCTION =
            h -> new HorseDto(h.getId(), h.getNickname(), h.getGenreEnum(), h.getAge(),
                    MAP_TO_OWNER_DTO_FUNCTION.apply(h.getOwner()));

    public static final Function<Jockey, JockeyDto> MAP_TO_JOCKEY_DTO_FUNCTION =
            j -> new JockeyDto(j.getId(), j.getName(), j.getAddress(), j.getAge(), j.getRating());

    public static final Function<RaceResult, RaceResultDto> MAP_TO_RACE_RESULT_DTO_FUNCTION =
            r -> new RaceResultDto(r.getId(), r.getPlace(), r.getHorceTime(), r.getRace().getId(),
                    MAP_TO_JOCKEY_DTO_FUNCTION.apply(r.getJockey()), MAP_TO_HORSE_DTO_FUNCTION.apply(r.getHorse()));

    public static final Function<Race, RaceDto> MAP_TO_RACE_DTO_FUNCTION =
            r -> new RaceDto(r.getId(), r.getName(), r.getRaceDate(), r.getRaceTime(), r.getLocation(),
                    r.getRaceResults().stream().map(MAP_TO_RACE_RESULT_DTO_FUNCTION).collect(Collectors.toList()));
}
