package ru.job4j.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.springdata.AccidentSpringDataRepository;
import ru.job4j.repository.springdata.RuleSpringDataRepository;
import ru.job4j.service.AccidentService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Реализация бизнесс логики c моделью Accident.
 * версия с использование SpringDataJpa
 */
@Service
@AllArgsConstructor
public class AccidentSpringDataService implements AccidentService {
    private final AccidentSpringDataRepository accidentSpringDataRep;
    private final RuleSpringDataRepository ruleSpringDataRep;

    @Override
    public Collection<Accident> findAll() {
        return (Collection<Accident>) accidentSpringDataRep.findAll();
    }

    @Override
    public Accident findById(int id) {
        return accidentSpringDataRep.findById(id).get();
    }

    @Override
    public void add(Accident accident, Set<Integer> rIds) {
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        accidentSpringDataRep.save(accident);
    }

    private Set<Rule> getRulesByRIds(Set<Integer> rIds) {
        Set<Rule> rules = new HashSet<>();
        if (rIds != null) {
            for (Integer rId : rIds) {
                Rule rule = ruleSpringDataRep.findById(rId).get();
                rules.add(rule);
            }
        }
        return rules;
    }

    @Override
    public void update(Accident accident, Set<Integer> rIds) {
        this.add(accident, rIds);
    }

    @Override
    public void delete(int id) {
        accidentSpringDataRep.deleteById(id);
    }

}
