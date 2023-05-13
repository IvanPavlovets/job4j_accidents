package ru.job4j.repository.jdbctemplate;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Rule;
import ru.job4j.repository.RuleRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Реализация RuleRepository с использованием JdbcTemplate
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
public class RuleJdbcTemplate implements RuleRepository {

    /**
     * объект для чтения записей из базы данных.
     */
    private final JdbcTemplate jdbc;

    @Override
    public Collection<Rule> findAll() {
        return jdbc.query(
                "select id, rule_name from rule", this::mapRowToRule
        );
    }

    @Override
    public Rule findById(int id) {
        Rule rule = (Rule) jdbc.queryForObject("select id, rule_name from rule where id = ?",
                this::mapRowToRule, id);
        return rule;
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
    private <T> T mapRowToRule(ResultSet row, int rowNum) throws SQLException {
        Rule rule = new Rule();
        rule.setId(row.getInt("id"));
        rule.setName(row.getString("rule_name"));
        return (T) rule;
    }
}
