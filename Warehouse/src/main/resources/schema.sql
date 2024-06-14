CREATE TABLE IF NOT EXISTS app_user_profile (
                                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                first_name VARCHAR(255),
    last_name VARCHAR(255),
    address VARCHAR(255),
    profile_picture_url VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS app_user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    profile_id BIGINT,
    CONSTRAINT fk_profile FOREIGN KEY (profile_id) REFERENCES app_user_profile (id)
);

CREATE TABLE IF NOT EXISTS app_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS app_user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    FOREIGN KEY (role_id) REFERENCES app_role(id)
);
CREATE TABLE IF NOT EXISTS app_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );


