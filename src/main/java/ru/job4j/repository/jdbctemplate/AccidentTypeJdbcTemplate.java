package ru.job4j.repository.jdbctemplate;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentTypeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Реализация AccidentTypeRepository с использованием JdbcTemplate
 *
 * "Spring в действии" Крейг Уоллс
 * Глава 3. Работа с данными
 * 3.1 Чтение и запись данных с помощью JDBC
 * 3.1.2 Использование JDBCTemplate
 * Шаблон - query(SQL, RowMapper, id)
 * 1) RowMapper - обрабатывает каждую запись БД, с помощью ResultSet
 * и возвращает готовый обьект;
 * 2) id - аргменты заещаемые символ ? в SQL-запросе.
 */
@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate implements AccidentTypeRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Collection<AccidentType> findAll() {
        return jdbc.query("select id, type_name from accident_type",
                this::mapRowToAccidentType);
    }

    @Override
    public AccidentType findById(int id) {
        AccidentType type = (AccidentType) jdbc.queryForObject(
                "select id, type_name from accident_type", this::mapRowToAccidentType, id);
        return type;
    }

    /**
     * Реализация RowMapper - обрабатывает отдельно каждую запись,
     * полученную из БД, и возвращает уже готовый объект – модель данных.
     * RowMapper пробегаеться по строкам БД с помощью ResultSet.
     * @param row каждую строку обрабатываем отдельно при помощи ResultSet.
     * @param rowNum
     * @return
     * @param <T>
     * @throws SQLException
     */
    private <T> T mapRowToAccidentType(ResultSet row, int rowNum) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(row.getInt("id"));
        type.setName(row.getString("type_name"));
        return (T) type;
    }
}
