package ru.job4j.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

/**
 * Класс конфигурации JdbcTemplate
 * JdbcConfig создает подключение и содержит пул соединений.
 * "@PropertySource" - аннотация говорит Spring считать файл.
 * "@Value" - получить настройки.
 * Метод ds загружает пул соединений.
 * Метод jdbc создает обертку для работы с базой.
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class JdbcConfig {
    @Bean
    public DataSource ds(@Value("${jdbc.url}") String url,
                         @Value("${jdbc.username}") String username,
                         @Value("${jdbc.${jdbc.password}}") String password,
                         @Value("${jdbc.driver}") String driver) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
