package ru.mpu.horse_races;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.domain.entities.*;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.repositories.RaceRepository;
import ru.mpu.horse_races.repositories.RaceResultRepository;
import ru.mpu.horse_races.services.RaceServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RaceServiceImplTest {

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private RaceResultRepository raceResultRepository;

    @InjectMocks
    private RaceServiceImpl raceService;

    private CreateOrUpdateRaceDtoRq createOrUpdateRaceDtoRq;
    private Race race;

    @BeforeEach
    void setup() {
        createOrUpdateRaceDtoRq = new CreateOrUpdateRaceDtoRq();
        createOrUpdateRaceDtoRq.setId(1L);
        createOrUpdateRaceDtoRq.setName("Spring Derby");
        createOrUpdateRaceDtoRq.setRaceDate(LocalDate.now());
        createOrUpdateRaceDtoRq.setRaceTime(LocalTime.now());
        createOrUpdateRaceDtoRq.setLocation("Stadium");

        race = new Race(1L, "Spring Derby", LocalDate.now(), LocalTime.now(), "Stadium", new ArrayList<>());
    }

    @Test
    public void insert_ValidRace_ReturnsRaceDto() {
        when(raceRepository.save(any(Race.class))).thenReturn(race);

        RaceDto result = raceService.insert(createOrUpdateRaceDtoRq);

        assertNotNull(result);
        assertEquals("Spring Derby", result.getName());
        verify(raceRepository, times(1)).save(any(Race.class));
    }

    @Test
    public void update_ValidRace_ReturnsRaceDto() {
        when(raceRepository.findById(1L)).thenReturn(Optional.of(race));
        when(raceRepository.save(any(Race.class))).thenReturn(race);

        RaceDto result = raceService.update(createOrUpdateRaceDtoRq);

        assertNotNull(result);
        assertEquals("Spring Derby", result.getName());
        verify(raceRepository, times(1)).findById(1L);
        verify(raceRepository, times(1)).save(any(Race.class));
    }

    @Test
    public void update_InvalidRace_ThrowsNotFoundException() {
        when(raceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> raceService.update(createOrUpdateRaceDtoRq));

        verify(raceRepository, times(1)).findById(1L);
        verify(raceRepository, never()).save(any(Race.class));
    }

    @Test
    public void findAll_ReturnsListOfRaceDto() {
        when(raceRepository.findAll()).thenReturn(Collections.singletonList(race));

        List<RaceDto> result = raceService.findAll();

        assertEquals(1, result.size());
        verify(raceRepository, times(1)).findAll();
    }

    @Test
    public void findById_ValidId_ReturnsRaceDto() {
        when(raceRepository.findById(1L)).thenReturn(Optional.of(race));

        RaceDto result = raceService.findById(1L);

        assertNotNull(result);
        assertEquals("Spring Derby", result.getName());
        verify(raceRepository, times(1)).findById(1L);
    }

    @Test
    public void findById_InvalidId_ThrowsNotFoundException() {
        when(raceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> raceService.findById(1L));

        verify(raceRepository, times(1)).findById(1L);
    }

    @Test
    public void deleteById_ValidId_DeletesRace() {
        raceService.deleteById(1L);

        verify(raceRepository, times(1)).deleteById(1L);
    }

    @Test
    public void findAllRaceRaceResults_ValidId_ReturnsListOfRaceResultDto() {
        // Создание и инициализация всех необходимых объектов
        Jockey jockey = new Jockey(1L, "John Doe", "123 Main St", (byte) 28, 95);
        Owner owner = new Owner(1L, "Owner Name", "Address", "123456789");
        Horse horse = new Horse(1L, "Speedster", "MALE", (byte) 5, owner); // Инициализируем Horse

        RaceResult raceResult = new RaceResult();
        raceResult.setId(1L);
        raceResult.setRace(race);
        raceResult.setJockey(jockey);
        raceResult.setHorse(horse); // Убедимся, что Horse не равен null

        when(raceResultRepository.findByRaceId(1L)).thenReturn(Collections.singletonList(raceResult));

        List<RaceResultDto> result = raceService.findAllRaceRaceResults(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(raceResultRepository, times(1)).findByRaceId(1L);
    }
}
