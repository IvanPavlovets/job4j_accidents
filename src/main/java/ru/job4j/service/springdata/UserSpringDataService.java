package ru.job4j.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.repository.springdata.UserSpringDataRepository;
import ru.job4j.service.UserService;

import java.util.Optional;

/**
 * Реализация бизнесс логики c моделью User.
 * версия с использование SpringDataJpa
 */
@Service
@AllArgsConstructor
public class UserSpringDataService implements UserService {
    private final UserSpringDataRepository usersRep;

    @Override
    public Optional<User> save(User user) {
        try {
            usersRep.save(user);
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }

}
