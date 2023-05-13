package ru.job4j.service;

import ru.job4j.model.AccidentType;

import java.util.Collection;

/**
 * Интерфейс описывает поведение слоя
 * бизнесс логики модели AccidentType
 */
public interface AccidentTypeService {
    Collection<AccidentType> findAll();

    AccidentType findById(int id);

}
