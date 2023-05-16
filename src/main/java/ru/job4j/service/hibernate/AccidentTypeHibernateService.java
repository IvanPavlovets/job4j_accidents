package ru.job4j.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.hibernate.AccidentTypeHibernate;
import ru.job4j.service.AccidentTypeService;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentTypeHibernateService implements AccidentTypeService {

    private final AccidentTypeHibernate hibernateRep;

    @Override
    public Collection<AccidentType> findAll() {
        return hibernateRep.findAll();
    }

    @Override
    public AccidentType findById(int id) {
        return hibernateRep.findById(id);
    }
}
