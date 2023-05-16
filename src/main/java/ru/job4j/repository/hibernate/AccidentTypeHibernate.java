package ru.job4j.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Map;

/**
 * Реализация хранилища модели AccidentType
 * версия с использование Spring ORM Hibernate
 */
@Repository
@AllArgsConstructor
public class AccidentTypeHibernate implements AccidentTypeRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<AccidentType> findAll() {
        return crudRepository.query(
                "FROM AccidentType", AccidentType.class
        );
    }

    @Override
    public AccidentType findById(int id) {
        return crudRepository.optional(
                        "FROM AccidentType WHERE id = :fId", AccidentType.class,
                        Map.of("fId", id))
                .get();
    }


}
