package ru.mpu.horse_races.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mpu.horse_races.domain.entities.RaceResult;
@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
}
