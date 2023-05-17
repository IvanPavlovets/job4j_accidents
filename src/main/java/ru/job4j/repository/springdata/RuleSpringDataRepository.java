package ru.job4j.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Rule;

/**
 * Реализация хранилища модели Rule
 * версия с использование SpringDataJpa
 * CrudRepository<Accident, Integer>
 *  первый параметр - тип хранимых объектов
 *  второй параметр - тип идентификатара хранимого обьекта
 */
public interface RuleSpringDataRepository extends CrudRepository<Rule, Integer> {

}
