package ru.job4j.repository;

import org.junit.jupiter.api.Test;
import ru.job4j.model.AccidentType;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccidentTypeMemTest {
    private final AccidentTypeMem types = new AccidentTypeMem();

    @Test
    public void whenfindAllThenReturnList() {
        AccidentType type1 = new AccidentType(1, "Две машины");
        AccidentType type2 = new AccidentType(2, "Машина и человек");
        AccidentType type3 = new AccidentType(3, "Машина и велосипед");

        Set<AccidentType> expectedAllType = Set.of(type1, type2, type3);
        var actualAllType = types.findAll();

        assertThat(actualAllType).usingRecursiveComparison().isEqualTo(expectedAllType);
    }

}
