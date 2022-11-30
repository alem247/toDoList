insert into users_tasks (user_id, task_id)
select users.id, tasks.id from users cross join tasks