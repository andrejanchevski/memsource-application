alter table users
    add column is_active boolean,
    add column is_expired boolean,
    add column is_locked boolean;