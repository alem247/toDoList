alter table tokens add column user_id int
constraint users_tokens_fk references users(id)
on update cascade on delete cascade