create table book(
    id serial primary key,
    isbn varchar not null unique ,
    title varchar not null ,
    genre varchar not null ,
    description varchar not null ,
    author varchar not null
)
