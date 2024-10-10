package ru.mpu.horse_races.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mpu.horse_races.domain.entities.Horse;

import java.util.List;
import java.util.Optional;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Long> {
    @Override
    @EntityGraph("horse-owner-entity-graph")
    Optional<Horse> findById(Long id);

    @Override
    @EntityGraph("horse-owner-entity-graph")
    List<Horse> findAll();
}
