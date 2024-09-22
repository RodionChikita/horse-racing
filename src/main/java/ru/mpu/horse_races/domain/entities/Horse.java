package ru.mpu.horse_races.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
