# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table artist (
  artist_id                 integer not null,
  name                      varchar(255),
  genre                     varchar(255),
  description               varchar(255),
  constraint pk_artist primary key (artist_id))
;

create table booking (
  booking_id                integer not null,
  date                      timestamp,
  event_id                  integer,
  id                        integer,
  constraint pk_booking primary key (booking_id))
;

create table collection (
  id                        integer not null,
  artist_id                 integer,
  name                      varchar(255),
  img_url                   varchar(255),
  description               varchar(255),
  constraint pk_collection primary key (id))
;

create table contact (
  msg_id                    integer not null,
  name                      varchar(255),
  email                     varchar(255),
  subject                   varchar(255),
  message                   varchar(255),
  status                    varchar(255),
  constraint pk_contact primary key (msg_id))
;

create table event (
  event_id                  integer not null,
  event_name                varchar(255),
  event_cat                 varchar(255),
  event_cost                varchar(255),
  event_description         varchar(255),
  event_date                timestamp,
  capacity                  integer,
  numbookings               integer,
  image_url                 varchar(255),
  constraint pk_event primary key (event_id))
;

create table review (
  review_id                 integer not null,
  user_id                   integer,
  username                  varchar(255),
  comment                   varchar(255),
  date_of                   timestamp,
  constraint pk_review primary key (review_id))
;

create table my_user (
  id                        integer not null,
  f_name                    varchar(255),
  l_name                    varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  confirm_password          varchar(255),
  tel                       varchar(255),
  add1                      varchar(255),
  add2                      varchar(255),
  country                   varchar(255),
  type                      integer,
  constraint pk_my_user primary key (id))
;

create sequence artist_seq;

create sequence booking_seq;

create sequence collection_seq;

create sequence contact_seq;

create sequence event_seq;

create sequence review_seq;

create sequence my_user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists artist;

drop table if exists booking;

drop table if exists collection;

drop table if exists contact;

drop table if exists event;

drop table if exists review;

drop table if exists my_user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists artist_seq;

drop sequence if exists booking_seq;

drop sequence if exists collection_seq;

drop sequence if exists contact_seq;

drop sequence if exists event_seq;

drop sequence if exists review_seq;

drop sequence if exists my_user_seq;

