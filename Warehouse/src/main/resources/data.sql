-- Insert initial profiles
INSERT INTO app_user_profile (first_name, last_name, address, profile_picture_url)
VALUES
    ('Alice', 'Smith', '456 Elm St', 'http://example.com/profile/alicesmith.jpg'),
    ('Bob', 'Johnson', '789 Oak St', 'http://example.com/profile/bobjohnson.jpg'),
    ('Carol', 'Williams', '101 Pine St', 'http://example.com/profile/carolwilliams.jpg'),
    ('David', 'Brown', '202 Maple St', 'http://example.com/profile/davidbrown.jpg'),
    ('Emma', 'Davis', '303 Birch St', 'http://example.com/profile/emmadavis.jpg'),
    ('Frank', 'Miller', '404 Cedar St', 'http://example.com/profile/frankmiller.jpg'),
    ('Grace', 'Wilson', '505 Cherry St', 'http://example.com/profile/gracewilson.jpg'),
    ('Henry', 'Moore', '606 Spruce St', 'http://example.com/profile/henrymoore.jpg');

-- Insert initial users with hashed passwords and associated profiles
INSERT INTO app_user (username, password, profile_id)
VALUES
    ('alicesmith', '$2a$12$Jrx62/JOfmEwohNryFTDX.2S6/B9/Mcv1jtUk/MeLmNAYSFxLyY26',
     (SELECT id FROM app_user_profile WHERE first_name = 'Alice' AND last_name = 'Smith')),
    ('bobjohnson', '$2a$12$Jrx62/JOfmEwohNryFTDX.2S6/B9/Mcv1jtUk/MeLmNAYSFxLyY26',
     (SELECT id FROM app_user_profile WHERE first_name = 'Bob' AND last_name = 'Johnson')),
    ('carolwilliams', '$2a$12$Jrx62/JOfmEwohNryFTDX.2S6/B9/Mcv1jtUk/MeLmNAYSFxLyY26',
     (SELECT id FROM app_user_profile WHERE first_name = 'Carol' AND last_name = 'Williams')),
    ('davidbrown', '$2a$12$Jrx62/JOfmEwohNryFTDX.2S6/B9/Mcv1jtUk/MeLmNAYSFxLyY26',
     (SELECT id FROM app_user_profile WHERE first_name = 'David' AND last_name = 'Brown')),
    ('emmadavis', '$2a$12$Jrx62/JOfmEwohNryFTDX.2S6/B9/Mcv1jtUk/MeLmNAYSFxLyY26',
     (SELECT id FROM app_user_profile WHERE first_name = 'Emma' AND last_name = 'Davis')),
    ('frankmiller', '$2a$12$Jrx62/JOfmEwohNryFTDX.2S6/B9/Mcv1jtUk/MeLmNAYSFxLyY26',
     (SELECT id FROM app_user_profile WHERE first_name = 'Frank' AND last_name = 'Miller')),
    ('gracewilson', '$2a$12$Jrx62/JOfmEwohNryFTDX.2S6/B9/Mcv1jtUk/MeLmNAYSFxLyY26',
     (SELECT id FROM app_user_profile WHERE first_name = 'Grace' AND last_name = 'Wilson')),
    ('henrymoore', '$2a$12$Jrx62/JOfmEwohNryFTDX.2S6/B9/Mcv1jtUk/MeLmNAYSFxLyY26',
     (SELECT id FROM app_user_profile WHERE first_name = 'Henry' AND last_name = 'Moore'));

-- Insert roles
INSERT INTO app_role(name)
VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN'),
    ('ROLE_STAFF');

-- Associate users with roles
INSERT INTO app_user_roles (user_id, role_id)
SELECT u.id, r.id
FROM app_user u, app_role r
WHERE r.name = 'ROLE_USER' AND u.username IN ('alicesmith', 'bobjohnson', 'emmadavis', 'frankmiller');

INSERT INTO app_user_roles (user_id, role_id)
SELECT u.id, r.id
FROM app_user u, app_role r
WHERE r.name = 'ROLE_ADMIN' AND u.username IN ('davidbrown', 'carolwilliams');

INSERT INTO app_user_roles (user_id, role_id)
SELECT u.id, r.id
FROM app_user u, app_role r
WHERE r.name = 'ROLE_STAFF' AND u.username IN ('davidbrown', 'gracewilson', 'henrymoore');

-- Insert products
INSERT INTO app_products(name)
VALUES
    ('Geld'),
    ('Termine'),
    ('Kunden Daten'),
    ('Produkt A'),
    ('Produkt B'),
    ('Produkt C'),
    ('Produkt D'),
    ('Produkt E');
