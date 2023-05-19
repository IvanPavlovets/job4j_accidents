package ru.job4j.service.jdbctemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.jdbctemplate.AccidentJdbcTemplate;
import ru.job4j.service.AccidentService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * AccidentService слой бизнесс логики
 * модели Accident.
 * Реализация JdbcTemplate
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Service
public class AccidentJdbcTemplateService implements AccidentService {

    private final AccidentJdbcTemplate accidentRep;

    @Override
    public Collection<Accident> findAll() {
        return accidentRep.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidentRep.findById(id));
    }

    @Override
    public Accident add(Accident accident, Set<Integer> rIds) {
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        accidentRep.add(accident);
        return accident;
    }

    private Set<Rule> getRulesByRIds(Set<Integer> rIds) {
        Set<Rule> set = new HashSet<>();
        for (Integer id : rIds) {
            Rule rule = new Rule(id, null);
            set.add(rule);
        }
        return set;
    }

    @Override
    public void update(Accident accident, Set<Integer> rIds) {
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        accidentRep.update(accident);
    }

    @Override
    public void delete(int id) {
        accidentRep.delete(id);
    }
}
