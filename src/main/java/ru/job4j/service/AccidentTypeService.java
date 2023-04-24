package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentMem;
import ru.job4j.repository.AccidentTypeMem;

import java.util.Collection;
import java.util.List;

/**
 * AccidentTypeService слой бизнесс
 * логики модели AccidentType.
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Service
public class AccidentTypeService {

    private final AccidentTypeMem typeMem;

    public List<AccidentType> findAll() {
        return typeMem.findAll().stream().toList();
    }

    public AccidentType findById(int id) {
        return typeMem.findById(id);
    }

}
