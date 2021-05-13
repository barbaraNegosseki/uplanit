create schema uplanit;

use uplanit;

create user 'bnegosseki'@'localhost' identified by 'pass123';

grant select, insert, delete, update on uplanit.* to bnegosseki@'localhost';

create table up_users (
  usr_name varchar(100) not null,
  usr_surname varchar(100) not null,
  usr_email varchar(50) not null,
  usr_birthday varchar(50) not null,
  usr_password varchar(100) not null,
  usr_ocupation varchar(100),
  usr_username varchar(100) not null,
  primary key (usr_username)
);

create table up_tasks (
  task_id varchar(10) not null, 
  task_name varchar(250) not null,
  task_check varchar(50) not null,
  task_date_created varchar(50) not null,
  task_date_due varchar(100) not null,
  primary key (task_id)
);

create table up_subscription (
  up_subscription_name varchar(50) not null,
  primary key (up_subscription_name)
);

create table up_user_subscription (
  usr_username varchar(50) not null,
  up_subscription_name varchar(50) not null,
  primary key (usr_username, up_subscription_name),
  foreign key usr_username_fk (usr_username) references up_users (usr_username),
  foreign key up_subscription_name_fk (up_subscription_name) references up_subscription (up_subscription_name)
);

insert into up_users (usr_name, usr_surname, usr_email, usr_birthday, usr_password, usr_ocupation, usr_username)
  values ('Admin','01','admin@gmail.com','11-11-1999','$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C','Administrador','admin');

insert into up_subscription (up_subscription_name)
  values ('SUBSCRIBED');

insert into up_subscription (up_subscription_name)
  values ('TRIAL');

insert into up_user_subscription (usr_username, up_subscription_name) 
  values ('admin', 'SUBSCRIBED');