CREATE TABLE tv_show (
    tv_show_id varchar(255),
    name varchar(255) NOT NULL,
    release_date varchar(255) NOT NULL,
    on_netflix BOOLEAN NOT NULL,
    primary key(tv_show_id)
)