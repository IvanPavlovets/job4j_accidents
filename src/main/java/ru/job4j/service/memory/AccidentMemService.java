package ru.job4j.service.memory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.memory.AccidentMem;
import ru.job4j.repository.memory.AccidentTypeMem;
import ru.job4j.repository.memory.RuleMem;
import ru.job4j.service.AccidentService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * AccidentService слой бизнесс логики
 * модели Accident.
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Service
public class AccidentMemService implements AccidentService {

    private final AccidentMem accidentMem;
    private final AccidentTypeMem typeMem;
    private final RuleMem ruleMem;

    /**
     * Вернуть все значения хранилища
     * @return List<Accident>
     */
    @Override
    public List<Accident> findAll() {
        return accidentMem.findAll().stream().toList();
    }

    /**
     * Accident добавить в хранилище
     *
     * @param accident
     * @param rIds
     * @return Accident
     */
    @Override
    public Accident add(Accident accident, Set<Integer> rIds) {
        var type = typeMem.findById(
                accident.getType().getId()
        );
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        accident.setType(type);
        accidentMem.add(accident);
        return accident;
    }

    private Set<Rule> getRulesByRIds(Set<Integer> rIds) {
        Set<Rule> rules = new HashSet<>();
        if (rIds != null) {
            for (Integer rId : rIds) {
                Rule rule = ruleMem.findById(rId);
                rules.add(rule);
            }
        }
        return rules;
    }

    /**
     * Найти по id Accident
     *
     * @param id
     * @return Accident
     */
    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidentMem.findById(id));
    }

    /**
     * Заменить Accident
     *
     * @param accident
     * @param rIds
     */
    @Override
    public void update(Accident accident, Set<Integer> rIds) {
        var type = typeMem.findById(
                accident.getType().getId()
        );
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        accident.setType(type);
        accidentMem.update(accident);
    }

    @Override
    public void delete(int id) {
        accidentMem.delete(id);
    }

}
