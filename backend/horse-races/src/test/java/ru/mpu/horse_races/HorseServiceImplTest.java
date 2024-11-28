package ru.mpu.horse_races;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateHorseDtoRq;
import ru.mpu.horse_races.domain.dtos.HorseDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.domain.entities.*;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.repositories.HorseRepository;
import ru.mpu.horse_races.repositories.OwnerRepository;
import ru.mpu.horse_races.repositories.RaceResultRepository;
import ru.mpu.horse_races.services.HorseServiceImpl;

import java.sql.Time;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HorseServiceImplTest {

    @Mock
    private HorseRepository horseRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private RaceResultRepository raceResultRepository;

    @InjectMocks
    private HorseServiceImpl horseService;

    private CreateOrUpdateHorseDtoRq createOrUpdateHorseDtoRq;

    private Owner owner;
    private Horse horse;
    private RaceResult raceResult;
    private HorseDto horseDto;
    private RaceResultDto raceResultDto;

    @BeforeEach
    void setup() {
        owner = new Owner(1L, "Owner Name", "Owner Address", "123456789");

        horse = new Horse(1L, "Bolt", "MALE", (byte) 4, owner);

        // Пример создания Time объекта для horseTime
        raceResult = new RaceResult(1L, "1", new Time(new Date().getTime()), new Race(), new Jockey(), horse);
        // Не забудьте создать объекты Race и Jockey в соответствии с ожидаемой логикой

        createOrUpdateHorseDtoRq = new CreateOrUpdateHorseDtoRq();
        createOrUpdateHorseDtoRq.setId(1L);
        createOrUpdateHorseDtoRq.setNickname("Bolt");
        createOrUpdateHorseDtoRq.setOwnerId(1L);
        createOrUpdateHorseDtoRq.setAge((byte) 4);
        createOrUpdateHorseDtoRq.setGenderEnum(GenderEnum.MALE);
    }

    @Test
    public void insert_ValidHorse_ReturnsHorseDto() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(horseRepository.save(any(Horse.class))).thenReturn(horse);

        HorseDto result = horseService.insert(createOrUpdateHorseDtoRq);

        assertNotNull(result);
        assertEquals("Bolt", result.getNickname());
        verify(ownerRepository, times(1)).findById(1L);
        verify(horseRepository, times(1)).save(any(Horse.class));
    }

    @Test
    public void insert_InvalidOwner_ThrowsNotFoundException() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> horseService.insert(createOrUpdateHorseDtoRq));

        verify(ownerRepository, times(1)).findById(1L);
        verify(horseRepository, never()).save(any(Horse.class));
    }

    @Test
    public void update_ValidHorse_ReturnsHorseDto() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(horseRepository.findById(1L)).thenReturn(Optional.of(horse));
        when(horseRepository.save(any(Horse.class))).thenReturn(horse);

        HorseDto result = horseService.update(createOrUpdateHorseDtoRq);

        assertNotNull(result);
        assertEquals("Bolt", result.getNickname());
        verify(ownerRepository, times(1)).findById(1L);
        verify(horseRepository, times(1)).findById(1L);
        verify(horseRepository, times(1)).save(any(Horse.class));
    }

    @Test
    public void update_InvalidHorse_ThrowsNotFoundException() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(horseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> horseService.update(createOrUpdateHorseDtoRq));

        verify(ownerRepository, times(1)).findById(1L);
        verify(horseRepository, times(1)).findById(1L);
        verify(horseRepository, never()).save(any(Horse.class));
    }

    @Test
    public void findAll_ReturnsListOfHorseDto() {
        when(horseRepository.findAll()).thenReturn(Collections.singletonList(horse));

        List<HorseDto> result = horseService.findAll();

        assertEquals(1, result.size());
        verify(horseRepository, times(1)).findAll();
    }

    @Test
    public void findAllHorseRaceResults_ValidId_ReturnsListOfRaceResultDto() {
        when(raceResultRepository.findByHorseId(1L)).thenReturn(Collections.singletonList(raceResult));

        List<RaceResultDto> result = horseService.findAllHorseRaceResults(1L);

        assertEquals(1, result.size());
        verify(raceResultRepository, times(1)).findByHorseId(1L);
    }

    @Test
    public void findById_ValidId_ReturnsHorseDto() {
        when(horseRepository.findById(1L)).thenReturn(Optional.of(horse));

        HorseDto result = horseService.findById(1L);

        assertNotNull(result);
        assertEquals("Bolt", result.getNickname());
        verify(horseRepository, times(1)).findById(1L);
    }

    @Test
    public void findById_InvalidId_ThrowsNotFoundException() {
        when(horseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> horseService.findById(1L));

        verify(horseRepository, times(1)).findById(1L);
    }

    @Test
    public void deleteById_ValidId_DeletesHorse() {
        horseService.deleteById(1L);

        verify(horseRepository, times(1)).deleteById(1L);
    }
}
