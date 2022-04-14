create table car
(
    id       serial not null,
    calendar timestamp,
    color    varchar(255),
    mark     varchar(255),
    number   varchar(255),
    year     int4   not null,
    primary key (id)
);