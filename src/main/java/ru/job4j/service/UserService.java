package ru.job4j.service;

import ru.job4j.model.User;

import java.util.Optional;

/**
 * Интерфейс описывает поведение слоя
 * бизнесс логики модели User
 */
public interface UserService {
    Optional<User> save(User user);
}
