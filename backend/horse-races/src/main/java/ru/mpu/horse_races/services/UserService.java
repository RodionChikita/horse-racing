package ru.mpu.horse_races.services;

import ru.mpu.horse_races.domain.entities.User;


public interface UserService {

    public User getLoggedUser();

    public void syncUser(User user);
}