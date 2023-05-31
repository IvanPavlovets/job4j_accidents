package ru.job4j.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Базовый класс конфигурации безопасности.
 * Служба хранения пользователей (через JDBC):
 * - configure(auth) - содержит описание, как искать пользователей.
 * Определеяем имя пользователя, пароль и роль(привелегии)
 * - configure(http) содержит описание доступов и конфигурирование
 * страницы входа в приложение.
 * "/login" - ссылка, доступна всем.
 * "/**" - ссылки доступны только пользователем с ролями ADMIN, USER.
 * .formLogin() - настройка формы авторизации (вход и выход).
 *
 */
@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource ds;

    /**
     * Настройка авторизации через JDBC
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(ds)
                .usersByUsernameQuery(
                        "select username, password, enabled from users "
                        + "where username = ?")
                .authoritiesByUsernameQuery(
                        " select u.username, a.authority "
                                + "from authorities as a, users as u "
                                + "where u.username = ? and u.authority_id = a.id");
    }

    /**
     * метод содержит:
     * - описание доступов к страницам;
     * - настройку страницы
     * входа в приложение ".formLogin()",
     * ошибку адресса ".failureUrl("/login?error=true")",
     * выхода ".logout()".
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/registration")
                .permitAll()
                .antMatchers("/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

}
