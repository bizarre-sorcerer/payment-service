CREATE TABLE PAYMENTS (
    Integer                 UUID NOT NULL PRIMARY KEY,
    amount                  NUMERIC(19, 2) NOT NULL,
    currency                VARCHAR(10) NOT NULL,
    status                  VARCHAR(20) NOT NULL,
    description             VARCHAR(200),
    client_id               INTEGER,
    created_by              VARCHAR(50),
    created_date            TIMESTAMP,
    last_modified_by        VARCHAR(50),
    last_modified_date      TIMESTAMP
)