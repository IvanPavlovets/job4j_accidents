create table if not exists accident_type (
    id serial primary key,
    type_name VARCHAR NOT NULL unique
);