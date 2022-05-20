create table users(
    id bigserial primary key,
    name text,
    user_name text,
    password text
);

create table roles(
    id bigserial primary key,
    name text
);

create table users_roles(
    id bigserial primary key,
    user_id bigserial references users(id),
    role_id bigserial references roles(id)
);