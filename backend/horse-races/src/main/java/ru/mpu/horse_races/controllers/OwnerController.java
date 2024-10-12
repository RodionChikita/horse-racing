package ru.mpu.horse_races.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mpu.horse_races.domain.dtos.CreateOrUpdateOwnerDtoRq;
import ru.mpu.horse_races.domain.dtos.OwnerDto;
import ru.mpu.horse_races.services.OwnerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owner")
@CrossOrigin(origins = "http://192.168.1.17:4200", maxAge = 3600)
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping
    public List<OwnerDto> getAllOwners() {
        return ownerService.findAll();
    }

    @PostMapping
    public ResponseEntity<OwnerDto> addOwner(@RequestBody CreateOrUpdateOwnerDtoRq owner) {
        OwnerDto newJockey = ownerService.insert(owner);
        return new ResponseEntity<>(newJockey, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OwnerDto> updateOwner(@RequestBody CreateOrUpdateOwnerDtoRq updatedOwner) {
        OwnerDto owner = ownerService.update(updatedOwner);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
