MERGE INTO authors (id, name) values (1, 'Пушкин');
ALTER TABLE authors ALTER COLUMN ID RESTART WITH 2;

MERGE INTO genres (id, name) values (1, 'Роман');
ALTER TABLE genres ALTER COLUMN ID RESTART WITH 2;

MERGE INTO books (id, name, author_id, genre_id) values (1, 'Капитанская дочка', 1, 1);
ALTER TABLE books ALTER COLUMN ID RESTART WITH 2;