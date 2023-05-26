package ru.job4j.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Базовый класс конфигурации безопасности.
 * Служба хранения пользователей в памяти:
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
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("USER", "ADMIN");
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
                .antMatchers("/login")
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
