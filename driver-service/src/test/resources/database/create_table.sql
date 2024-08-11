CREATE TABLE IF NOT EXISTS currencies
(
    id   UUID PRIMARY KEY NOT NULL,
    name VARCHAR(50)      NOT NULL,
    code VARCHAR(3)       NOT NULL
    );