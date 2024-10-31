create table
owners(
    id bigserial,
    name varchar(255),
    address varchar(255),
    phone_number varchar(255),
    primary key (id)
);

create table
horses(
    id bigserial,
    nickname varchar(255),
    gender varchar(255),
    age smallint,
    owner_id bigint references owners(id) on delete cascade,
    primary key (id)
);


create table
jockeys(
    id bigserial,
    name varchar(255),
    address varchar(255),
    age smallint,
    rating int,
    primary key (id)
);

create table
races(
    id bigserial,
    name varchar(255),
    race_date date,
    race_time time,
    location varchar(255),
    primary key (id)
);

create table
race_results(
    id bigserial,
    place varchar(255),
    horse_time time,
    race_id bigint references races(id) on delete cascade,
    jockey_id bigint references jockeys(id) on delete cascade,
    horse_id bigint references horses(id) on delete cascade,
    primary key (id)
);

insert into owners(name, address, phone_number)
values ('Owner_1', 'Address_1', '88888888881'), ('Owner_2', 'Address_2', '88888888882'), ('Owner_3', 'Address_3', '88888888883');

insert into horses(nickname, gender, age, owner_id)
values ('Nickname_1', 'MALE', 15, 1), ('Nickname_2', 'FEMALE', 20, 2), ('Nickname_3', 'MALE', 17, 3);

insert into jockeys(name, address, age, rating)
values ('Jockey_1', 'Address_1', 20, 34), ('Jockey_2', 'Address_2', 30, 45), ('Jockey_3', 'Address_3', 40, 64);

--insert into races(name, race_date, race_time, location)
--values (1, 1),   (1, 2),
--       (2, 3),   (2, 4),
--       (3, 5),   (3, 6);
--
--insert into race_results(place, horse_time, race_id, jockey_id, horse_id)
--values ('comment_01', 1), ('comment_02', 1), ('comment_03', 1), ('comment_04', 1), ('comment_05', 1), ('comment_06', 2),
--       ('comment_07', 2), ('comment_08',3), ('comment_09', 3), ('comment_10', 3), ('comment_11', 3), ('comment_12', 3);