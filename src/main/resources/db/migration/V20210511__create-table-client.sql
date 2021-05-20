-- public.clients definition

-- drop table

-- drop table public.clients;

create table if not exists public.clients (
	id bigserial not null primary key,
	"name" varchar(60) not null,
	email varchar(255) not null,
	phone varchar(20) not null
);