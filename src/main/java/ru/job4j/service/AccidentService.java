package ru.job4j.service;

import ru.job4j.model.Accident;

import java.util.Collection;
import java.util.Set;

/**
 * Интерфейс описывает поведение слоя
 * бизнесс логики модели Accident
 */
public interface AccidentService {
    Collection<Accident> findAll();

    Accident findById(int id);

    void add(Accident accident, Set<Integer> rIds);

    void update(Accident accident, Set<Integer> rIds);

    void delete(int id);

}
