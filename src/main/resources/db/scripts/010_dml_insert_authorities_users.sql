-- вносим в тоблицу ролей, возможные роли пользователей
insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

-- вносим в таблицу пользователей данные
-- вносим пороль '123' в PasswordEncoder для пользователя user:
insert into users (username, enabled, password, authority_id)
values ('user', true, '$2a$10$eGK6qNirNeAL9XNbjCXFDOgb./RyLzdykTYRiX79FJXElZveDFNF6',
        (select id from authorities where authority = 'ROLE_USER'));
-- пороль 'root' в PasswordEncoder для пользователя admin:
insert into users (username, enabled, password, authority_id)
values ('admin', true, '$2a$10$cTVgo0vge6vRe8PRLFZlzeHVkUJQ6E3JSdybA2RZn4Aez6bbmakd2',
        (select id from authorities where authority = 'ROLE_ADMIN'));