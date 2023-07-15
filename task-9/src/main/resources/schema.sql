CREATE TABLE IF NOT EXISTS authors (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS genres (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS books (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    author_id BIGINT,
    genre_id BIGINT,
    CONSTRAINT fk_book_author_id FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
    CONSTRAINT fk_book_genre_id FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comments (
    id IDENTITY PRIMARY KEY,
    text VARCHAR(255),
    book_id BIGINT,
    CONSTRAINT fk_comment_book_id FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

