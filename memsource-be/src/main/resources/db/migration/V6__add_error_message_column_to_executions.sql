alter table scheduled_executions
    add column error_message text;

alter table scheduled_executions
    add column scheduled_execution_state int not null;