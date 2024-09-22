package ru.mpu.horse_races.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mpu.horse_races.domain.entities.Race;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
}
