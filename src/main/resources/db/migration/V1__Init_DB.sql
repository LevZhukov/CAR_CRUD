
    drop table if exists car cascade;

    drop sequence if exists car_seq;

    create sequence car_seq start with 1 increment by 50;

    create table car (
        cost integer not null,
        id integer not null,
        issue_date date,
        manufacturer smallint check (manufacturer between 0 and 3),
        model varchar(255),
        primary key (id)
    );