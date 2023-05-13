create table if not exists accident (
    id serial primary key,
    a_name VARCHAR,
    text TEXT,
    address VARCHAR,
    type_id INT REFERENCES accident_type(id)
);