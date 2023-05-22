alter table if exists public.post
drop constraint if exists fk_posts_author_id;

drop table if exists public.author cascade;
drop table if exists public.post cascade;
drop sequence if exists author_id_seq;
drop sequence if exists post_id_seq;

create table public.author (
       id bigint not null,
        email_address varchar(255),
        first_name varchar(255) not null,
        full_name varchar(255) not null,
        second_name varchar(255) not null,
        primary key (id)
    );

create table public.post (
       id bigint not null,
        header varchar(255) not null,
        name varchar(255) not null,
        publish_date timestamp(6) not null,
        rating integer,
        text varchar(255) not null,
        author_id bigint,
        primary key (id)
    );

create sequence author_id_seq start with 1 increment by 1;
create sequence post_id_seq start with 1 increment by 1;

alter table if exists public.post
      add constraint fk_posts_author_id
      foreign key (author_id)
      references public.author;