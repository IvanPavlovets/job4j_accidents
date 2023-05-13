package ru.job4j.service;

import ru.job4j.model.Rule;

import java.util.Collection;

/**
 * Интерфейс описывает поведение слоя
 * бизнесс логики модели Rule
 */
public interface RuleService {
    Collection<Rule> findAll();

    Rule findById(int id);

}
