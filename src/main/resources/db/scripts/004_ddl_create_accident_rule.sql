-- accident_rule связующая таблица, many to many, таблицы accident и rule
create table if not exists accident_rule (
    id serial primary key,
    accident_id INT NOT NULL REFERENCES accident(id),
    rule_id INT NOT NULL REFERENCES rule(id),
    CONSTRAINT accident_id_rule_id UNIQUE (accident_id, rule_id)
);