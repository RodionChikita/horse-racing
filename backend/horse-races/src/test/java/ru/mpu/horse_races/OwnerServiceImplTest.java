package ru.mpu.horse_races;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateOwnerDtoRq;
import ru.mpu.horse_races.domain.dtos.OwnerDto;
import ru.mpu.horse_races.domain.entities.Owner;
import ru.mpu.horse_races.exceptions.NotFoundException;
import ru.mpu.horse_races.repositories.OwnerRepository;
import ru.mpu.horse_races.services.OwnerServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    private CreateOrUpdateOwnerDtoRq createOrUpdateOwnerDtoRq;
    private Owner owner;

    @BeforeEach
    void setup() {
        createOrUpdateOwnerDtoRq = new CreateOrUpdateOwnerDtoRq();
        createOrUpdateOwnerDtoRq.setId(1L);
        createOrUpdateOwnerDtoRq.setName("John Doe");
        createOrUpdateOwnerDtoRq.setAddress("123 Main St");
        createOrUpdateOwnerDtoRq.setPhoneNumber("123456789");

        owner = new Owner(1L, "John Doe", "123 Main St", "123456789");
    }

    @Test
    public void insert_ValidOwner_ReturnsOwnerDto() {
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);

        OwnerDto result = ownerService.insert(createOrUpdateOwnerDtoRq);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(ownerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    public void update_ValidOwner_ReturnsOwnerDto() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);

        OwnerDto result = ownerService.update(createOrUpdateOwnerDtoRq);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(ownerRepository, times(1)).findById(1L);
        verify(ownerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    public void update_InvalidOwner_ThrowsNotFoundException() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> ownerService.update(createOrUpdateOwnerDtoRq));

        verify(ownerRepository, times(1)).findById(1L);
        verify(ownerRepository, never()).save(any(Owner.class));
    }

    @Test
    public void findAll_ReturnsListOfOwnerDto() {
        when(ownerRepository.findAll()).thenReturn(Collections.singletonList(owner));

        List<OwnerDto> result = ownerService.findAll();

        assertEquals(1, result.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    public void findById_ValidId_ReturnsOwnerDto() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));

        OwnerDto result = ownerService.findById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(ownerRepository, times(1)).findById(1L);
    }

    @Test
    public void findById_InvalidId_ThrowsNotFoundException() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> ownerService.findById(1L));

        verify(ownerRepository, times(1)).findById(1L);
    }

    @Test
    public void deleteById_ValidId_DeletesOwner() {
        ownerService.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(1L);
    }
}
