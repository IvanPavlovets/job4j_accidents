package ru.job4j.service;

import ru.job4j.model.Rule;

import java.util.Optional;

/**
 * Интерфейс описывает поведение слоя
 * бизнесс логики модели Rule
 */
public interface RuleService {
    Iterable<Rule> findAll();

    Optional<Rule> findById(int id);

}
