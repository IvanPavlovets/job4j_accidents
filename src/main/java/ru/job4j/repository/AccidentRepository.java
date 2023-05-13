package ru.job4j.repository;

import ru.job4j.model.Accident;

import java.util.Collection;

/**
 * Интерфейс описывает поведение модели Accident
 * "Spring в действии" Крейг Уоллс
 * Глава 3. Работа с данными
 * 3.1 Чтение и запись данных с помощью JDBC
 * 3.1.2 Использование JDBCTemplate
 * Определение репозиториев JDBC:
 * - получение всех Accident в виде коллекции обьектов;
 * - получение одного Accident по id;
 * - CRUD операции
 */
public interface AccidentRepository {
    Collection<Accident> findAll();

    Accident findById(int id);

    void add(Accident accident);

    void update(Accident accident);

    void delete(int id);

}
