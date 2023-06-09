package ru.job4j.service.jdbctemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.jdbctemplate.AccidentTypeJdbcTemplate;
import ru.job4j.service.AccidentTypeService;

import java.util.Collection;
import java.util.Optional;

/**
 * AccidentTypeService слой бизнесс
 * логики модели AccidentType.
 * Реализация JdbcTemplate
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Service
public class AccidentTypeJdbcTemplateService implements AccidentTypeService {

    private final AccidentTypeJdbcTemplate repository;

    @Override
    public Collection<AccidentType> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(repository.findById(id));
    }
}
