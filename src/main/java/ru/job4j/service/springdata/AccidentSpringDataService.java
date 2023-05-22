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
    private final RuleSpringDataRepository ruleSpringDataRep;

    @Override
    public Iterable<Accident> findAll() {
        return accidentSpringDataRep.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentSpringDataRep.findById(id);
    }

    /**
     * метод вставки Accident.
     * Дочерние обьекты rule получаем
     * по набору rIds, с помощью "IN clause"
     * реализованого в методе
     * Iterable<T> findAllById(Iterable<ID> ids),
     * из шаблона CRUD spring data репозиория.
     * @param accident
     * @param rIds
     * @return
     */
    @Override
    public Accident add(Accident accident, Set<Integer> rIds) {
        Set<Rule> rules = new HashSet<>((Collection) ruleSpringDataRep.findAllById(rIds));
        accident.setRules(rules);
        return accidentSpringDataRep.save(accident);
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
