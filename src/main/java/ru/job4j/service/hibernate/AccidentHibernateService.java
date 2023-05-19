package ru.job4j.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.hibernate.AccidentHibernate;
import ru.job4j.service.AccidentService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentHibernateService implements AccidentService {

    private final AccidentHibernate hibernateRep;

    @Override
    public Collection<Accident> findAll() {
        return hibernateRep.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(hibernateRep.findById(id));
    }

    @Override
    public Accident add(Accident accident, Set<Integer> rIds) {
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        hibernateRep.add(accident);
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
        hibernateRep.update(accident);
    }

    @Override
    public void delete(int id) {
        hibernateRep.delete(id);
    }
}
