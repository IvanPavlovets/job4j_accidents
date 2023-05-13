package ru.job4j.service.jdbctemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.jdbctemplate.AccidentJdbcTemplate;
import ru.job4j.repository.jdbctemplate.RuleJdbcTemplate;
import ru.job4j.service.AccidentService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final RuleJdbcTemplate ruleRep;

    @Override
    public Collection<Accident> findAll() {
        return accidentRep.findAll();
    }

    @Override
    public Accident findById(int id) {
        return accidentRep.findById(id);
    }

    @Override
    public void add(Accident accident, Set<Integer> rIds) {
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        accidentRep.add(accident);
    }

    private Set<Rule> getRulesByRIds(Set<Integer> rIds) {
        Set<Rule> rules = new HashSet<>();
        if (rIds != null) {
            for (Integer rId : rIds) {
                Rule rule = ruleRep.findById(rId);
                rules.add(rule);
            }
        }
        return rules;
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
