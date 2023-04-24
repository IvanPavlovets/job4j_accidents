package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentMem;
import ru.job4j.repository.AccidentTypeMem;

import java.util.List;

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

    /**
     * Вернуть все значения хранилища
     * @return List<Accident>
     */
    public List<Accident> findAll() {
        return accidentMem.findAll().stream().toList();
    }

    /**
     * Accident добавить в хранилище
     * @param accident
     */
    public void add(Accident accident) {
        var type = typeMem.findById(
                accident.getType().getId()
        );
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
     * @param accident
     */
    public void update(Accident accident) {
        var type = typeMem.findById(
                accident.getType().getId()
        );
        accident.setType(type);
        accidentMem.update(accident);
    }

}
