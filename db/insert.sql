create table car
(
    id       serial    not null,
    calendar timestamp not null,
    color    varchar(255),
    mark     varchar(255),
    number   varchar(255),
    year     int4      not null,
    primary key (id)
);
insert into car(calendar, color, mark, number, year)
VALUES (CURRENT_TIMESTAMP, 'black', 'DODGE', 'A589AA177Rus', 2011),
       (CURRENT_TIMESTAMP, 'white', 'FORD', 'A117AA177Rus', 2018),
    (CURRENT_TIMESTAMP, 'Green', 'Chevrolet', 'R089AA89Rus', 2021);