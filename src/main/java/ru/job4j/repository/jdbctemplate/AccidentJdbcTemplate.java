package ru.job4j.repository.jdbctemplate;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;
import ru.job4j.model.Rule;
import ru.job4j.repository.AccidentRepository;
import ru.job4j.repository.jdbctemplate.rowmapper.AccidentRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRepository {

    private final JdbcTemplate jdbc;

    /**
     * 1) keyHolder.getKey() now contains the generated key
     * <p>
     * 2) saveAccidentRule(accident) - Сохранение в таблицу accident_rule
     * данных по статьям присвоенным accident
     *
     * @param accident
     * @return
     */
    @Override
    public void add(Accident accident) {
        final String INSERT_SQL = "INSERT INTO accident(a_name, text, address, type_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId(keyHolder.getKey().intValue());
        saveAccidentRule(accident);
    }

    /**
     * Сохранение соответсвующих id (rule и accident)
     * в связующую таблицу accident_rule.
     *
     * @param accident Accident
     */
    private void saveAccidentRule(Accident accident) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accident_rule(accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(), rule.getId());
        }
    }

    @Override
    public void update(Accident accident) {
        int result = jdbc.update("update accident"
                        + " SET a_name = ?, text = ?, address = ?, type_id = ?"
                        + " WHERE id = ?",
                accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType().getId(),
                accident.getId());
        if (result > 0) {
            deleteAccidentRule(accident.getId());
            saveAccidentRule(accident);
        }
    }

    @Override
    public Collection<Accident> findAll() {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        jdbc.query("SELECT * FROM accident AS ac "
                        + "LEFT JOIN accident_type AS at ON ac.type_id = at.id "
                        + "LEFT JOIN accident_rule AS ar ON ac.id = ar.accident_id "
                        + "LEFT JOIN rule AS r ON ar.rule_id = r.id",
                new AccidentRowMapper(accidentMap));
        return accidentMap.values();
    }

    @Override
    public Accident findById(int id) {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        jdbc.query("SELECT * FROM accident AS ac "
                        + "LEFT JOIN accident_type AS at ON ac.type_id = at.id "
                        + "LEFT JOIN accident_rule AS ar ON ac.id = ar.accident_id "
                        + "LEFT JOIN rule AS r ON ar.rule_id = r.id "
                        + "WHERE ac.id = ?",
                new AccidentRowMapper(accidentMap), id);
        return accidentMap.get(id);
    }

    private <T> T mapRowToAccidentType(ResultSet row, int rowNum) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(row.getInt("id"));
        type.setName(row.getString("type_name"));
        return (T) type;
    }


    @Override
    public void delete(int id) {
        deleteAccidentRule(id);
        int result = jdbc.update("delete from accident where id = ?", id);
    }

    /**
     * Удаление rule в связующей таблице
     * accident_rule по передному accident_id
     *
     * @param id соответсвуещего обьекта accident
     */
    private void deleteAccidentRule(int id) {
        jdbc.update("delete from accident_rule where accident_id = ?", id);
    }
}
