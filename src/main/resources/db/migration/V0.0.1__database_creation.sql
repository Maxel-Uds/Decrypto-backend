CREATE TABLE message (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    message TEXT NOT NULL,
    password TEXT NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);