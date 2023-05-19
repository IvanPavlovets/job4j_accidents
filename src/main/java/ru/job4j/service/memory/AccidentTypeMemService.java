package ru.job4j.service.memory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.memory.AccidentTypeMem;
import ru.job4j.service.AccidentTypeService;

import java.util.List;
import java.util.Optional;

/**
 * AccidentTypeService слой бизнесс
 * логики модели AccidentType.
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Service
public class AccidentTypeMemService implements AccidentTypeService {

    private final AccidentTypeMem typeMem;

    public List<AccidentType> findAll() {
        return typeMem.findAll().stream().toList();
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(typeMem.findById(id));
    }

}
