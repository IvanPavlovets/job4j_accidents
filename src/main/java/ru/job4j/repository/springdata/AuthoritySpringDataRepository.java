package ru.job4j.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Authority;

public interface AuthoritySpringDataRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
