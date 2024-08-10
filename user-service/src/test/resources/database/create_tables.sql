CREATE TABLE IF NOT EXISTS client_app
(
    id          UUID PRIMARY KEY NOT NULL,
    user_name   VARCHAR(255)     NOT NULL UNIQUE,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    email       VARCHAR(255)     NOT NULL UNIQUE,
    password    VARCHAR(255)     NOT NULL,
    phone_number VARCHAR(20),
    address     VARCHAR(255),
    city        VARCHAR(100),
    country     VARCHAR(100),
    is_active   BOOLEAN,
    created_at  TIMESTAMP        NOT NULL,
    updated_at  TIMESTAMP        NOT NULL
    );
