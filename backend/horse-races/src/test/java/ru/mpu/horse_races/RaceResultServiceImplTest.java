package ru.mpu.horse_races;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateRaceResultDtoRq;
import ru.mpu.horse_races.domain.dtos.RaceResultDto;
import ru.mpu.horse_races.domain.entities.*;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.repositories.*;
import ru.mpu.horse_races.services.RaceResultServiceImpl;

import java.sql.Time;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RaceResultServiceImplTest {

    @Mock
    private RaceResultRepository raceResultRepository;

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private HorseRepository horseRepository;

    @Mock
    private JockeyRepository jockeyRepository;

    @InjectMocks
    private RaceResultServiceImpl raceResultService;

    private CreateOrUpdateRaceResultDtoRq createOrUpdateRaceResultDtoRq;
    private RaceResult raceResult;
    private Race race;
    private Horse horse;
    private Jockey jockey;
    private Owner owner;

    @BeforeEach
    void setup() {
        race = new Race(1L, "Spring Derby", null, null, "Stadium", null);
        owner = new Owner(1L, "Owner Name", "Address", "123456789");
        horse = new Horse(1L, "Speedster", "MALE", (byte) 5, owner);
        jockey = new Jockey(1L, "John Doe", "123 Main St", (byte) 28, 95);

        createOrUpdateRaceResultDtoRq = new CreateOrUpdateRaceResultDtoRq();
        createOrUpdateRaceResultDtoRq.setId(1L);
        createOrUpdateRaceResultDtoRq.setRaceId(1L);
        createOrUpdateRaceResultDtoRq.setHorseId(1L);
        createOrUpdateRaceResultDtoRq.setJockeyId(1L);
        createOrUpdateRaceResultDtoRq.setPlace("1st");
        createOrUpdateRaceResultDtoRq.setHorceTime(new Time(System.currentTimeMillis()));

        raceResult = new RaceResult(1L, "1st", new Time(System.currentTimeMillis()), race, jockey, horse);
    }

    @Test
    public void insert_ValidRaceResult_ReturnsRaceResultDto() {
        when(raceRepository.findById(1L)).thenReturn(Optional.of(race));
        when(horseRepository.findById(1L)).thenReturn(Optional.of(horse));
        when(jockeyRepository.findById(1L)).thenReturn(Optional.of(jockey));
        when(raceResultRepository.save(any(RaceResult.class))).thenReturn(raceResult);

        RaceResultDto result = raceResultService.insert(createOrUpdateRaceResultDtoRq);

        assertNotNull(result);
        assertEquals("1st", result.getPlace());
        verify(raceRepository, times(1)).findById(1L);
        verify(horseRepository, times(1)).findById(1L);
        verify(jockeyRepository, times(1)).findById(1L);
        verify(raceResultRepository, times(1)).save(any(RaceResult.class));
    }

    @Test
    public void update_ValidRaceResult_ReturnsRaceResultDto() {
        when(raceRepository.findById(1L)).thenReturn(Optional.of(race));
        when(horseRepository.findById(1L)).thenReturn(Optional.of(horse));
        when(jockeyRepository.findById(1L)).thenReturn(Optional.of(jockey));
        when(raceResultRepository.findById(1L)).thenReturn(Optional.of(raceResult));
        when(raceResultRepository.save(any(RaceResult.class))).thenReturn(raceResult);

        RaceResultDto result = raceResultService.update(createOrUpdateRaceResultDtoRq);

        assertNotNull(result);
        assertEquals("1st", result.getPlace());
        verify(raceRepository, times(1)).findById(1L);
        verify(horseRepository, times(1)).findById(1L);
        verify(jockeyRepository, times(1)).findById(1L);
        verify(raceResultRepository, times(1)).findById(1L);
    }

    @Test
    public void update_InvalidRaceResult_ThrowsNotFoundException() {
        when(raceRepository.findById(1L)).thenReturn(Optional.of(race));
        when(horseRepository.findById(1L)).thenReturn(Optional.of(horse));
        when(jockeyRepository.findById(1L)).thenReturn(Optional.of(jockey));
        when(raceResultRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> raceResultService.update(createOrUpdateRaceResultDtoRq));

        verify(raceResultRepository, times(1)).findById(1L);
    }

    @Test
    public void findById_ValidId_ReturnsRaceResultDto() {
        when(raceResultRepository.findById(1L)).thenReturn(Optional.of(raceResult));

        RaceResultDto result = raceResultService.findById(1L);

        assertNotNull(result);
        assertEquals("1st", result.getPlace());
        verify(raceResultRepository, times(1)).findById(1L);
    }

    @Test
    public void findById_InvalidId_ThrowsNotFoundException() {
        when(raceResultRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> raceResultService.findById(1L));

        verify(raceResultRepository, times(1)).findById(1L);
    }

    @Test
    public void deleteById_ValidId_DeletesRaceResult() {
        raceResultService.deleteById(1L);

        verify(raceResultRepository, times(1)).deleteById(1L);
    }
}
