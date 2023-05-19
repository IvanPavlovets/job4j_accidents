package ru.job4j.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.springdata.AccidentTypeSpringDataRepository;
import ru.job4j.service.AccidentTypeService;

import java.util.Optional;

/**
 * Реализация бизнесс логики c моделью AccidentType.
 * версия с использование SpringDataJpa
 */
@Service
@AllArgsConstructor
public class AccidentTypeSpringDataService implements AccidentTypeService {
    private final AccidentTypeSpringDataRepository typeSpringDataRepository;

    @Override
    public Iterable<AccidentType> findAll() {
        return typeSpringDataRepository.findAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return typeSpringDataRepository.findById(id);
    }
}
