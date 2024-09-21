package ru.mpu.horse_races.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horses")
public class Horse {
    @Id
    private Long id;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "genre", nullable = false)
    private GenreEnum genreEnum;
    @Column(name = "age", nullable = false)
    private byte age;
    @OneToOne(targetEntity = Owner.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
