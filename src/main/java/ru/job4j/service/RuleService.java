package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Rule;
import ru.job4j.repository.RuleMem;

import java.util.List;


/**
 * RuleService слой бизнесс
 * логики модели Rule.
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Service
public class RuleService {

    private final RuleMem ruleMem;

    public List<Rule> findAll() {
        return ruleMem.findAll().stream().toList();
    }

    public Rule findById(int id) {
        return ruleMem.findById(id);
    }

}
