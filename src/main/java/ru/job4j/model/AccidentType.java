package ru.job4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Вложеный обьект для модели
 * Accident
 *
 * @author Ivan Pavlovets
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccidentType {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
}
