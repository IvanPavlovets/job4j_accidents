package ru.job4j.service;

import ru.job4j.model.User;

/**
 * Интерфейс описывает поведение слоя
 * бизнесс логики модели User
 */
public interface UserService {
    User save(User user);
}
