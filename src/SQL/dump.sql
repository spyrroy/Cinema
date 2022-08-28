USE cinema;

insert into genre (genre_id, name)
values (1, 'Триллер'),
       (2, 'Боевик'),
       (3, 'Комедия'),
       (4, 'Ужасы');

insert into seat (seat_id, number)
values (1, '1'),
       (2, '2'),
       (3, '3'),
       (4, '4'),
       (5, '5'),
       (6, '6'),
       (7, '7'),
       (8, '8'),
       (9, '9'),
       (10, '10'),
       (11, '11'),
       (12, '12'),
       (13, '13'),
       (14, '14'),
       (15, '15'),
       (16, '16'),
       (17, '17'),
       (18, '18'),
       (19, '19'),
       (20, '20');

insert into film (film_id, name, description, duration, genre_id)
values (1, 'Мстители', 'Фильм про Мстителей', '140', 3),
       (2, 'Халк', 'Фильм про Халка', '110', 1),
       (3, 'Тор', 'Фильм про Тора', '120', 2);

insert into session (session_id, date, time, film_id)
values (1, '2022-07-20', '9:00', 1),
       (2, '2022-07-20', '12:00', 2),
       (3, '2022-07-20', '15:00', 3);

insert into role (role_id, role)
values (1, 'Admin'),
       (2, 'User');

insert into user (user_id, login, first_name, second_name, password, salt, role_id)
values (1, 'admin', 'Admin', 'Adminovich', 'mbTrvHIPVW6ROx13J5shzbCtS/71IOjvJbJnTMIsdaI=', 'F0cqWSqXHH11LFktuzO7aarCgj2ux5', 1),
       (2, 'Vanko', 'Ivan', 'Ivanenko', 'LNvVLYEDR+WKbUwoCZLMtoT1+mur3xUzamign8aFEOE=', 'Lnpz8pihmKFOPZ0wbiGqn1ShqU0Wlq', 2);

insert into ticket (ticket_id, user_id, seat_id, session_id, date)
values (1, 1, 1, 1, now()),
       (2, 2, 12, 3, now());



