create table if not exists rule (
    id serial primary key,
    rule_name VARCHAR NOT NULL unique
);