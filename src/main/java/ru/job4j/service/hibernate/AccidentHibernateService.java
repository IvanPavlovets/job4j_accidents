package ru.job4j.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.hibernate.AccidentHibernate;
import ru.job4j.repository.hibernate.AccidentTypeHibernate;
import ru.job4j.repository.hibernate.RuleHibernate;
import ru.job4j.service.AccidentService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentHibernateService implements AccidentService {

    private final AccidentHibernate hibernateRep;
    private final AccidentTypeHibernate typeHibernateRep;
    private final RuleHibernate ruleHibernateRep;

    @Override
    public Collection<Accident> findAll() {
        return hibernateRep.findAll();
    }

    @Override
    public Accident findById(int id) {
        return hibernateRep.findById(id);
    }

    @Override
    public void add(Accident accident, Set<Integer> rIds) {
        var type = typeHibernateRep.findById(
                accident.getType().getId()
        );
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        accident.setType(type);
        hibernateRep.add(accident);
    }

    private Set<Rule> getRulesByRIds(Set<Integer> rIds) {
        Set<Rule> rules = new HashSet<>();
        if (rIds != null) {
            for (Integer rId : rIds) {
                Rule rule = ruleHibernateRep.findById(rId);
                rules.add(rule);
            }
        }
        return rules;
    }

    @Override
    public void update(Accident accident, Set<Integer> rIds) {
        var type = typeHibernateRep.findById(
                accident.getType().getId()
        );
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        accident.setType(type);
        hibernateRep.update(accident);
    }

    @Override
    public void delete(int id) {
        hibernateRep.delete(id);
    }
}
