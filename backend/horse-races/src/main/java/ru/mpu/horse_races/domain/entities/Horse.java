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
@NamedEntityGraph(name = "horse-owner-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("owner")
        })
public class Horse {
    @Id
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum genderEnum;

    @Column(name = "age", nullable = false)
    private byte age;

    @ManyToOne(targetEntity = Owner.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
