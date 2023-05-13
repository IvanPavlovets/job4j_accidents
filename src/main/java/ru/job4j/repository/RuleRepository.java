package ru.job4j.repository;

import ru.job4j.model.Rule;

import java.util.Collection;

/**
 * Интерфейс описывает поведение модели Rule
 * "Spring в действии" Крейг Уоллс
 * Глава 3. Работа с данными
 * 3.1 Чтение и запись данных с помощью JDBC
 * 3.1.2 Использование JDBCTemplate
 * Определение репозиториев JDBC:
 * - получение всех Rule в виде коллекции обьектов;
 * - получение одного Rule по id;
 */
public interface RuleRepository {
    Collection<Rule> findAll();

    Rule findById(int id);

}
