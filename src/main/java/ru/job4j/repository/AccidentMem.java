package ru.job4j.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище обьектов совершеных правонорущений.
 * Весрия с хранением в памяти.
 */
@Data
@Repository
public class AccidentMem {

    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(4);

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Fielder&Ipsum", "bumper", "turn to semaphornay street"));
        accidents.put(2, new Accident(2, "Note&Kalina", "door damage", "Leningradsy prospect 59"));
        accidents.put(3, new Accident(3, "BmwX5&Bus", "frontal collision", "Mira street 191"));
    }

    /**
     * Геттер всех значений хранилища
     * @return List<Accident>
     */
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    /**
     * Метод добавляет accident в
     * хранилище accidents(map) по ключу.
     * @param accident
     */
    public void add(Accident accident) {
        accident.setId(ACCIDENT_ID.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    /**
     * Обновляет запись Accident.
     * старая запись по id меняеться на
     * переданый accident.
     * @param accident
     */
    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

}
