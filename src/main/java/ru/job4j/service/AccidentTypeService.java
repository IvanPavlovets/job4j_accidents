package ru.job4j.service;

import ru.job4j.model.AccidentType;

import java.util.Optional;

/**
 * Интерфейс описывает поведение слоя
 * бизнесс логики модели AccidentType
 */
public interface AccidentTypeService {
    Iterable<AccidentType> findAll();

    Optional<AccidentType> findById(int id);

}
