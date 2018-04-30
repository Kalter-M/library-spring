create table book (isbn varchar(13) primary key, author varchar(255) not null, name varchar(255) not null);
create table user (id number primary key, login varchar(255) not null, password varchar(255) not null);

insert into user values (1, "admin", "admin");