package ru.job4j.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Rule;
import ru.job4j.repository.springdata.RuleSpringDataRepository;
import ru.job4j.service.RuleService;

import java.util.Collection;

/**
 * Реализация бизнесс логики c моделью Rule.
 * версия с использование SpringDataJpa
 */
@Service
@AllArgsConstructor
public class RuleSpringDataService implements RuleService {
    private final RuleSpringDataRepository ruleSpringDataRepository;

    @Override
    public Collection<Rule> findAll() {
        return (Collection<Rule>) ruleSpringDataRepository.findAll();
    }

    @Override
    public Rule findById(int id) {
        return ruleSpringDataRepository.findById(id).get();
    }

}
