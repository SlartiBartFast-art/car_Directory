create table car
(
    id       serial not null,
    calendar timestamp without time zone not null default now(),
    color    varchar(255),
    mark     varchar(255),
    number   varchar(255),
    year     int4   not null,
    primary key (id)
);