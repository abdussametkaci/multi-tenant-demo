CREATE TABLE product
(
    id               UUID         NOT NULL,
    name             VARCHAR(255) NOT NULL,
    price            DECIMAL      NOT NULL,
    version          BIGINT       NOT NULL,
    created_at       TIMESTAMPTZ  NOT NULL,
    last_modified_at TIMESTAMPTZ  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);
