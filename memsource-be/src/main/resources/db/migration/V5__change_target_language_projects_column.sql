alter table projects drop column target_language;

create table target_languages
(
    id   bigserial primary key,
    name text
);

create table projects_target_languages
(
    id                 bigserial primary key,
    project_id         bigserial references projects (id),
    target_language_id bigserial references target_languages (id)
);