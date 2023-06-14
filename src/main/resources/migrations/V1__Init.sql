-- Можно было бы сделать и через обычный SERIAL
-- но тогда надо угадывать с названием SEQUENC'а
-- и подставлять его в Entity. Потому мы сделаем
-- объявление счётчика более явным.

CREATE SEQUENCE products_id_seq;

CREATE TABLE products
(
    id           INTEGER     NOT NULL DEFAULT nextval('products_id_seq'),
    serial       VARCHAR(64) NOT NULL,
    manufacturer VARCHAR(64) NOT NULL,
    price        NUMERIC     NOT NULL,
    amount       INTEGER     NOT NULL DEFAULT 0,
    type         SMALLINT    NOT NULL,
    properties   VARCHAR(32),

    PRIMARY KEY (id),
    UNIQUE (serial)
);

ALTER SEQUENCE products_id_seq OWNED BY products.id;
