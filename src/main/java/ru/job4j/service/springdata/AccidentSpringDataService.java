package ru.job4j.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.springdata.AccidentSpringDataRepository;
import ru.job4j.service.AccidentService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Реализация бизнесс логики c моделью Accident.
 * версия с использование SpringDataJpa
 */
@Service
@AllArgsConstructor
public class AccidentSpringDataService implements AccidentService {
    private final AccidentSpringDataRepository accidentSpringDataRep;

    @Override
    public Iterable<Accident> findAll() {
        return accidentSpringDataRep.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentSpringDataRep.findById(id);
    }

    @Override
    public Accident add(Accident accident, Set<Integer> rIds) {
        Set<Rule> rules = getRulesByRIds(rIds);
        accident.setRules(rules);
        return accidentSpringDataRep.save(accident);
    }

    /**
     * метод получения Set<Rule> по полученыму
     * набору rIds. Передаем в БД Set<Rule>
     * с пустыми полями, в свою очередь хранилище
     * офильтрует обьекты, при условии:
     * 1) ограничений связующей таблицы
     * 2) аннатаций корневого и дочернего класов
     * @param rIds Set<Integer>
     * @return Set<Rule>
     */
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
        this.add(accident, rIds);
    }

    @Override
    public void delete(int id) {
        accidentSpringDataRep.deleteById(id);
    }

}
