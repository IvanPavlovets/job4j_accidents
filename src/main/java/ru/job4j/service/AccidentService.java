package ru.job4j.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentMem;

import java.util.List;

@Data
@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

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
        accidentMem.update(accident);
    }

}
