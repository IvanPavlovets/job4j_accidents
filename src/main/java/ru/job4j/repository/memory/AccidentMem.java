package ru.job4j.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище обьектов совершеных правонорущений.
 * Весрия с хранением в памяти.
 *
 * @author Ivan Pavlovets
 */
@Repository
public class AccidentMem implements AccidentRepository {

    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(4);

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AccidentTypeMem types = new AccidentTypeMem();
    private final RuleMem rules = new RuleMem();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Fielder&Ipsum", "bumper",
                "turn to semaphornay street", types.findById(1), Set.of(rules.findById(1)))
        );
        accidents.put(2, new Accident(2, "Note&Kalina", "door damage",
                "Leningradsy prospect 59", types.findById(2), Set.of(rules.findById(2), rules.findById(1)))
        );
        accidents.put(3, new Accident(3, "BmwX5&Bus", "frontal collision",
                "Mira street 191", types.findById(3), Set.of(rules.findById(1), rules.findById(3)))
        );
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

    @Override
    public void delete(int id) {
        accidents.remove(id);
    }

}
