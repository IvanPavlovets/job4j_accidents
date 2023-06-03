package ru.job4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableTransactionManagement
public class JdbcConfig {

    @Bean
    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
