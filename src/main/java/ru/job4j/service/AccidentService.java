package ru.job4j.service;

import ru.job4j.model.Accident;

import java.util.Optional;
import java.util.Set;

/**
 * Интерфейс описывает поведение слоя
 * бизнесс логики модели Accident
 */
public interface AccidentService {
    Iterable<Accident> findAll();

    Optional<Accident> findById(int id);

    Accident add(Accident accident, Set<Integer> rIds);

    void update(Accident accident, Set<Integer> rIds);

    void delete(int id);

}
