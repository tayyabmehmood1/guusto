CREATE TABLE IF NOT EXISTS balance (
       id int8 not null,
        balance int8 not null,
        client_id int8 UNIQUE not null,
        created_at TIMESTAMP WITHOUT TIME ZONE not null,
        primary key (id)
    );