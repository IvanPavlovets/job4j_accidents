package ru.job4j.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Accident;

/**
 * Реализация хранилища модели Accident
 * версия с использование SpringDataJpa
 * CrudRepository<Accident, Integer>
 * первый параметр - тип хранимых объектов
 * второй параметр - тип идентификатара хранимого обьекта
 */
public interface AccidentSpringDataRepository extends CrudRepository<Accident, Integer> {
}
