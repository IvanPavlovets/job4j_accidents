package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.AccidentType;
import ru.job4j.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище вложеных обьектов
 * Rule.
 * Весрия с хранением в памяти.
 *
 * @author Ivan Pavlovets
 */
@Repository
public class RuleMem {
    private static final AtomicInteger ID = new AtomicInteger();
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        rules.put(ID.incrementAndGet(), new Rule(ID.intValue(), "Статья. 1"));
        rules.put(ID.incrementAndGet(), new Rule(ID.intValue(), "Статья. 2"));
        rules.put(ID.incrementAndGet(), new Rule(ID.intValue(), "Статья. 3"));
    }

    public Collection<Rule> findAll() {
        return rules.values();
    }

    public Rule findById(int id) {
        return rules.get(id);
    }
}
