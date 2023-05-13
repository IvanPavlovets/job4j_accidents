package ru.job4j.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище вложеных обьектов
 * AccidentType.
 * Весрия с хранением в памяти.
 *
 * @author Ivan Pavlovets
 */
@Repository
public class AccidentTypeMem implements AccidentTypeRepository {
    private static final AtomicInteger ID = new AtomicInteger();

    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        accidentTypes.put(ID.incrementAndGet(), new AccidentType(ID.intValue(), "Две машины"));
        accidentTypes.put(ID.incrementAndGet(), new AccidentType(ID.intValue(), "Машина и человек"));
        accidentTypes.put(ID.incrementAndGet(), new AccidentType(ID.intValue(), "Машина и велосипед"));
    }

    /**
     * Геттер всех значений хранилища
     *
     * @return Collection<AccidentType>
     */
    public Collection<AccidentType> findAll() {
        return accidentTypes.values();
    }

    /**
     * Найди AccidentType по id
     *
     * @param id искомого обьекта
     * @return AccidentType
     */
    public AccidentType findById(int id) {
        return accidentTypes.get(id);
    }
}
