package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.AccidentMem;
import ru.job4j.repository.AccidentTypeMem;
import ru.job4j.repository.RuleMem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * AccidentService слой бизнесс логики
 * модели Accident.
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Service
public class AccidentService {

    private final AccidentMem accidentMem;
    private final AccidentTypeMem typeMem;
    private final RuleMem ruleMem;

    /**
     * Вернуть все значения хранилища
     * @return List<Accident>
     */
    public List<Accident> findAll() {
        return accidentMem.findAll().stream().toList();
    }

    /**
     * Accident добавить в хранилище
     *
     * @param accident
     * @param rIds
     */
    public void add(Accident accident, Set<Integer> rIds) {
        var type = typeMem.findById(
                accident.getType().getId()
        );
        Set<Rule> rules = new HashSet<>();
        for (Integer rId : rIds) {
            Rule rule = ruleMem.findById(rId);
            rules.add(rule);
        }
        accident.setRules(rules);
        accident.setType(type);
        accidentMem.add(accident);
    }

    /**
     * Найти по id Accident
     * @param id
     * @return Accident
     */
    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    /**
     * Заменить Accident
     *
     * @param accident
     * @param rIds
     */
    public void update(Accident accident, Set<Integer> rIds) {
        var type = typeMem.findById(
                accident.getType().getId()
        );
        Set<Rule> rules = new HashSet<>();
        for (Integer rId : rIds) {
            Rule rule = ruleMem.findById(rId);
            rules.add(rule);
        }
        accident.setRules(rules);
        accident.setType(type);
        accidentMem.update(accident);
    }

}
