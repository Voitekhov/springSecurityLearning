DROP TABLE IF EXISTS opt;
DROP TABLE IF EXISTS users;
CREATE SEQUENCE global_seq START WITH 100000;
CREATE TABLE users
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);

CREATE TABLE opt
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    opt     VARCHAR NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
)
