package ru.job4j.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentRepository;

import java.util.Collection;
import java.util.Map;

/**
 * Реализация хранилища модели Accident
 * версия с использование Spring ORM Hibernate
 */
@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentRepository {

    private final CrudRepository crudRepository;

    private static final String HQL_FETCH = new StringBuilder()
            .append("left join fetch ac.type t ")
            .append("left join fetch ac.rules r")
            .toString();

    @Override
    public Collection<Accident> findAll() {
        return (Collection<Accident>) crudRepository.query(
                "select distinct ac from Accident ac "
                        + HQL_FETCH + " ORDER BY ac.id ASC",
                Accident.class
        );
    }

    @Override
    public Accident findById(int id) {
        return crudRepository.optional(
                        "FROM Accident AS ac "
                                + HQL_FETCH + " where ac.id = :fId", Accident.class,
                        Map.of("fId", id))
                .get();
    }

    /**
     * Добавление модели в контекст персистенции.
     *
     * @param accident
     */
    @Override
    public void add(Accident accident) {
        crudRepository.run(session -> session.save(accident));
    }

    @Override
    public void update(Accident accident) {
        try {
            crudRepository.run(session -> session.merge(accident));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        crudRepository.run(
                "DELETE Accident WHERE id = :fId",
                Map.of("fId", id)
        );
    }

}
