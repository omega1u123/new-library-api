create table book(
    id serial primary key,
    isbn varchar not null unique ,
    title varchar not null ,
    genre varchar not null ,
    description varchar not null ,
    author varchar not null
);

create table user_table(
    id serial primary key,
    username varchar not null unique,
    password varchar not null
);

insert into user_table (username, password) values ('user', '123')