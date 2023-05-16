package ru.job4j.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Rule;
import ru.job4j.repository.RuleRepository;

import java.util.Collection;
import java.util.Map;

/**
 * Реализация хранилища модели Rule
 * версия с использование Spring ORM Hibernate
 */
@Repository
@AllArgsConstructor
public class RuleHibernate implements RuleRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Rule> findAll() {
        return crudRepository.query(
                "FROM Rule", Rule.class
        );
    }

    @Override
    public Rule findById(int id) {
        return crudRepository.optional(
                        "FROM Rule WHERE id = :fId", Rule.class,
                        Map.of("fId", id))
                .get();
    }


}
