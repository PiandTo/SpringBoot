DROP SCHEMA if exists Springboot CASCADE ;

CREATE SCHEMA Springboot
    CREATE TABLE Springboot.hall (
        id serial primary key,
        name text,
        number integer,
        availableSeats integer
    );

    CREATE TABLE Springboot.film (
        id serial primary key,
        title text,
        year varchar (10),
        age integer,
        description text,
        poster text
    );

    CREATE TABLE Springboot.session (
        id serial primary key,
        date_time timestamp,
        hall_id INT REFERENCES Springboot.hall(id),
        film_id INT REFERENCES Springboot.film(id),
        ticket_cost DECIMAL(7,2)
    );

CREATE TABLE Springboot.users (
       user_id SERIAL PRIMARY KEY,
       first_name  VARCHAR(255) NOT NULL,
       last_name VARCHAR(255) NOT NULL,
       email VARCHAR(100) NOT NULL,
       phone_number VARCHAR(100) NOT NULL,
       password VARCHAR(100) NOT NULL,
       role_id INT REFERENCES  springboot_authority(authorities_id),
       status VARCHAR(255),
       avatar VARCHAR(255),
       is_non_locked bool,
       is_non_enable bool,
       fail_attempts int
);

CREATE TABLE Springboot.authority (
    authorities_id SERIAL PRIMARY KEY,
    name text
);

CREATE TABLE Springboot.messages (
         id serial primary key,
         username text,
         content text,
         type text,
         film_id INT REFERENCES  Springboot.film(id)
);

CREATE TABLE Springboot.logs
(
        log_id SERIAL PRIMARY KEY,
        email VARCHAR(100) NOT NULL ,
        ip VARCHAR(100) NOT NULL,
        time VARCHAR(100) NOT NULL,
        date VARCHAR(100) NOT NULL
);

CREATE TABLE Springboot.users_authorities (
       user_id int not null,
       authorities_id int not null,
       primary key (user_id, authorities_id),
       foreign key (user_id) references springboot.users(user_id) on update cascade,
       foreign key (authorities_id) references springboot.authority(authorities_id)  on update cascade
);

CREATE TABLE Springboot.email_verification (
    verification_id varchar(50) not null,
    username varchar(100),
    primary key (verification_id)
)