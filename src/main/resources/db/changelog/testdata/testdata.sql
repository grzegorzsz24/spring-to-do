INSERT INTO
    users(first_name, last_name, email, password)
VALUES
    ('Grzegorz', 'Szymanek', 'grzesio.szymanek@gmail.com', 'haslo123'),
    ('Anna', 'Szymanek', 'ania@gmail.com', '$2a$10$bQGwGmPF1OMLuEUwYs.33.QMdxnoOtqaiW72P8DxECMDESH3FJuaK');

INSERT INTO
    tasks(description, user_id)
VALUES
    ('Zadanie numer 1.', 1);

INSERT INTO
    roles(role)
VALUES
    ('admin'),('uzytkownik');

INSERT INTO
    users_roles(user_id, role_id)
VALUES
    (1, 1),
    (2, 1);