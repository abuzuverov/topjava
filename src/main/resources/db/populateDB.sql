DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', 'admin'),
  ('User', 'user@yandex.ru', 'password');

INSERT INTO user_roles (role, user_id) SELECT 'ROLE_ADMIN', id FROM users WHERE name = 'Admin';
INSERT INTO user_roles (role, user_id) SELECT 'ROLE_USER', id FROM users WHERE name = 'User';

/*INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', select id from users where name = 'Admin'),
  ('ROLE_USER', 100001);*/

INSERT INTO meals (date_time, description, calories, user_id)
  SELECT TIMESTAMP '2018-05-30 10:00:00', 'Завтрак', 500, id FROM users WHERE name = 'User';
INSERT INTO meals (date_time, description, calories, user_id)
  SELECT TIMESTAMP '2018-05-30 13:00:00', 'Обед', 1000, id FROM users WHERE name = 'User';
INSERT INTO meals (date_time, description, calories, user_id)
  SELECT TIMESTAMP '2018-05-30 20:00:00', 'Ужин', 500, id FROM users WHERE name = 'User';

INSERT INTO meals (date_time, description, calories, user_id)
  SELECT TIMESTAMP '2018-05-31 10:00:00', 'Завтрак', 500, id FROM users WHERE name = 'User';
INSERT INTO meals (date_time, description, calories, user_id)
  SELECT TIMESTAMP '2018-05-31 13:00:00', 'Обед', 1000, id FROM users WHERE name = 'User';
INSERT INTO meals (date_time, description, calories, user_id)
  SELECT TIMESTAMP '2018-05-31 20:00:00', 'Ужин', 510, id FROM users WHERE name = 'User';
