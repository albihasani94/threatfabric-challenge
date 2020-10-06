create table device
(
    id         uuid not null primary key,
    model      varchar(255),
    os_version varchar(255),
    type       varchar(255)
);
