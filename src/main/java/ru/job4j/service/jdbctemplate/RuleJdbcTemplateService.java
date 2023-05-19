package ru.job4j.service.jdbctemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Rule;
import ru.job4j.repository.jdbctemplate.RuleJdbcTemplate;
import ru.job4j.service.RuleService;

import java.util.Collection;
import java.util.Optional;

/**
 * RuleService слой бизнесс
 * логики модели Rule.
 * Реализация JdbcTemplate
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Service
public class RuleJdbcTemplateService implements RuleService {

    private final RuleJdbcTemplate repository;

    @Override
    public Collection<Rule> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(repository.findById(id));
    }
}
