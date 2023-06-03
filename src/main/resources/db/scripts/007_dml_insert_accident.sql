INSERT INTO accident (a_name, text, address, type_id)
VALUES ('догнал на светофоре', 'повреждения бампера',
        'г.Красноярск поворот тамбовская семафорная',
        (SELECT id from accident_type WHERE type_name='Две машины'));

INSERT INTO accident (a_name, text, address, type_id)
VALUES ('лобовое столкновение', 'повреждения капотов обоих машин',
        'г.Железногорск, Ленинградский проспект д.59',
        (SELECT id from accident_type WHERE type_name='Две машины'));

INSERT INTO accident (a_name, text, address, type_id)
VALUES ('наезд на пешехода', 'потерпевший отделался легким испугом',
        'г.Красноярск, Мира д.191',
        (SELECT id from accident_type WHERE type_name='Машина и человек'));