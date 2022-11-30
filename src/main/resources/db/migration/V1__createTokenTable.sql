create table tokens (
    id integer not null primary key,
    token varchar(255) not null,
    creation_time timestamp without time zone,
    expiration_time timestamp without time zone
)