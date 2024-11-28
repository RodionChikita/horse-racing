package ru.mpu.horse_races;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateJockeyDtoRq;
import ru.mpu.horse_races.domain.dtos.JockeyDto;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.domain.entities.*;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.repositories.JockeyRepository;
import ru.mpu.horse_races.repositories.RaceResultRepository;
import ru.mpu.horse_races.services.JockeyServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JockeyServiceImplTest {

    @Mock
    private JockeyRepository jockeyRepository;

    @Mock
    private RaceResultRepository raceResultRepository;

    @InjectMocks
    private JockeyServiceImpl jockeyService;

    private CreateOrUpdateJockeyDtoRq createOrUpdateJockeyDtoRq;
    private Jockey jockey;

    @BeforeEach
    void setup() {
        createOrUpdateJockeyDtoRq = new CreateOrUpdateJockeyDtoRq();
        createOrUpdateJockeyDtoRq.setId(1L);
        createOrUpdateJockeyDtoRq.setName("John Doe");
        createOrUpdateJockeyDtoRq.setAddress("123 Main St");
        createOrUpdateJockeyDtoRq.setAge((byte) 30);
        createOrUpdateJockeyDtoRq.setRating(100);

        jockey = new Jockey(1L, "John Doe", "123 Main St", (byte) 30, 100);
    }

    @Test
    public void insert_ValidJockey_ReturnsJockeyDto() {
        when(jockeyRepository.save(any(Jockey.class))).thenReturn(jockey);

        JockeyDto result = jockeyService.insert(createOrUpdateJockeyDtoRq);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(jockeyRepository, times(1)).save(any(Jockey.class));
    }

    @Test
    public void update_ValidJockey_ReturnsJockeyDto() {
        when(jockeyRepository.findById(1L)).thenReturn(Optional.of(jockey));
        when(jockeyRepository.save(any(Jockey.class))).thenReturn(jockey);

        JockeyDto result = jockeyService.update(createOrUpdateJockeyDtoRq);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(jockeyRepository, times(1)).findById(1L);
        verify(jockeyRepository, times(1)).save(any(Jockey.class));
    }

    @Test
    public void update_InvalidJockey_ThrowsNotFoundException() {
        when(jockeyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> jockeyService.update(createOrUpdateJockeyDtoRq));

        verify(jockeyRepository, times(1)).findById(1L);
        verify(jockeyRepository, never()).save(any(Jockey.class));
    }

    @Test
    public void findAll_ReturnsListOfJockeyDto() {
        when(jockeyRepository.findAll()).thenReturn(Collections.singletonList(jockey));

        List<JockeyDto> result = jockeyService.findAll();

        assertEquals(1, result.size());
        verify(jockeyRepository, times(1)).findAll();
    }

    @Test
    public void deleteById_ValidId_DeletesJockey() {
        jockeyService.deleteById(1L);

        verify(jockeyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void findAllJockeyRaceResults_ValidId_ReturnsListOfRaceResultDto() {
        Race race = new Race();
        race.setId(1L);

        Jockey jockey = new Jockey();
        jockey.setId(1L);

        Owner owner = new Owner();
        owner.setId(1L);  // Инициализируем пользователя

        Horse horse = new Horse();
        horse.setId(1L);
        horse.setGenderEnum("MALE");
        horse.setOwner(owner);  // Присваиваем проинициализированного Owner

        RaceResult raceResult = new RaceResult();
        raceResult.setRace(race);
        raceResult.setJockey(jockey);
        raceResult.setHorse(horse);

        when(raceResultRepository.findByJockeyId(1L)).thenReturn(Collections.singletonList(raceResult));

        List<RaceResultDto> result = jockeyService.findAllJockeyRaceResults(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(raceResultRepository, times(1)).findByJockeyId(1L);
    }


    @Test
    public void findById_ValidId_ReturnsJockeyDto() {
        when(jockeyRepository.findById(1L)).thenReturn(Optional.of(jockey));

        JockeyDto result = jockeyService.findById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(jockeyRepository, times(1)).findById(1L);
    }

    @Test
    public void findById_InvalidId_ThrowsNotFoundException() {
        when(jockeyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> jockeyService.findById(1L));

        verify(jockeyRepository, times(1)).findById(1L);
    }
}
