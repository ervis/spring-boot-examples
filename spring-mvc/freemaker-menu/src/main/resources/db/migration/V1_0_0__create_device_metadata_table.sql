CREATE TABLE IF NOT EXISTS device_metadata
(
    id      VARCHAR(255),
    user_id VARCHAR(255) NOT NULL,
    details VARCHAR(255) NOT NULL,
    login VARCHAR(31)  NOT NULL, -- can be either diff|screenshot
    last_login TIMESTAMP,
    PRIMARY KEY (id)
);
