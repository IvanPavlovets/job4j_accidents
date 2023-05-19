package ru.job4j.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Rule;
import ru.job4j.repository.hibernate.RuleHibernate;
import ru.job4j.service.RuleService;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleHibernateService implements RuleService {

    private final RuleHibernate hibernateRep;

    @Override
    public Collection<Rule> findAll() {
        return hibernateRep.findAll();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(hibernateRep.findById(id));
    }

}
