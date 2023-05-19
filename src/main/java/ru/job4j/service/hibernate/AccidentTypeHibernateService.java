package ru.job4j.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.hibernate.AccidentTypeHibernate;
import ru.job4j.service.AccidentTypeService;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeHibernateService implements AccidentTypeService {

    private final AccidentTypeHibernate hibernateRep;

    @Override
    public Collection<AccidentType> findAll() {
        return hibernateRep.findAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(hibernateRep.findById(id));
    }
}
