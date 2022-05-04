create table car
(
    id       bigserial                   not null,
    calendar timestamp without time zone not null default now(),
    modified timestamp,
    mark     varchar(255),
    number   varchar(255),
    year     int4                        not null,
    color_id int8 references colors (id),
    primary key (id)
);
create table colors
(
    id       bigserial not null,
    coloring varchar(255),
    primary key (id)
);