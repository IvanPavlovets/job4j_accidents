package ru.job4j.repository;

import ru.job4j.model.AccidentType;

import java.util.Collection;

/**
 * Интерфейс описывает поведение модели AccidentType
 * "Spring в действии" Крейг Уоллс
 * Глава 3. Работа с данными
 * 3.1 Чтение и запись данных с помощью JDBC
 * 3.1.2 Использование JDBCTemplate
 * Определение репозиториев JDBC:
 * - получение всех AccidentType в виде коллекции обьектов;
 * - получение одного AccidentType по id;
 */
public interface AccidentTypeRepository {
    Collection<AccidentType> findAll();

    AccidentType findById(int id);

}
