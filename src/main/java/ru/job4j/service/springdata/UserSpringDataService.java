package ru.job4j.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.repository.springdata.UserSpringDataRepository;
import ru.job4j.service.UserService;

/**
 * Реализация бизнесс логики c моделью User.
 * версия с использование SpringDataJpa
 */
@Service
@AllArgsConstructor
public class UserSpringDataService implements UserService {
    private final UserSpringDataRepository usersRep;

    @Override
    public User save(User user) {
        return usersRep.save(user);
    }
}
