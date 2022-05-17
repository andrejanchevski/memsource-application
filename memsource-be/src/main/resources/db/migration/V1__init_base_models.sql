create table projects(
    id bigserial primary key,
    external_id bigserial not null unique,
    project_name text not null,
    source_language text not null,
    target_language text not null,
    project_status int not null,
    description text,
    date_created timestamp with time zone
);

create table scheduled_executions(
    id bigserial primary key,
    date_started timestamp with time zone not null,
    date_finished timestamp with time zone,
    is_started boolean,
    is_finished boolean
);