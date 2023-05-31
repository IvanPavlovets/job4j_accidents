package ru.job4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Go to http://localhost:8080/index");

        System.out.println("пороль '123' для пользователя user:\n"
                + genPassword("123"));
        System.out.println("пороль 'root' для пользователя admin:\n"
                + genPassword("root"));
    }

    private static String genPassword(String pswrd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pswrd);
    }


}