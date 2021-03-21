create schema uplanit;

use uplanit;

create user 'bnegosseki'@'localhost' identified by 'pass123';

grant select, insert, delete, update on uplanit.* to user@'bnegosseki';

create table up_users (
  usr_usr_name varchar(100) not null,
  usr_usr_surname varchar(100) not null,
  usr_usr_email varchar(50) not null,
  usr_usr_birthday varchar(50) not null,
  usr_usr_password varchar(100) not null,
  usr_usr_ocupation varchar(100),
  usr_usr_username varchar(100) not null,
  primary key (usr_usr_username)
);

create table up_user_type (
  up_usr_type_name varchar(50) not null,
  primary key (up_usr_type_name)
);

create table up_user_type_subscription (
  usr_usr_username varchar(50) not null,
  up_usr_type_name varchar(50) not null,
  primary key (usr_usr_username, up_usr_type_name),
  foreign key usr_usr_username_fk (usr_usr_username) references up_users (usr_usr_username),
  foreign key up_usr_type_name_fk (up_usr_type_name) references up_user_type (up_usr_type_name)
);

insert into up_users (
  usr_usr_name, 
  usr_usr_surname,
  usr_usr_email,
  usr_usr_birthday,
  usr_usr_password,
  usr_usr_ocupation,
  usr_usr_username)
    values (
      'Barbara',
      'Negosseki',
      'barbara@gmail.com',
      '07-10-1997',
      'pass123',
      'Estudante',
      'bnegosseki');

insert into up_user_type (up_usr_type_name)
  values ('TRIAL', 'SUBSCRIBED');

insert into up_user_type_subscription (usr_usr_username, up_usr_type_name) 
  values ('bnegosseki', 'SUBSCRIBED');