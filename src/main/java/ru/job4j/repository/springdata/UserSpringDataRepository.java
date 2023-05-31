package ru.job4j.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.User;

public interface UserSpringDataRepository extends CrudRepository<User, Integer> {
}
