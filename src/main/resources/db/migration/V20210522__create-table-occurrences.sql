-- drop table

-- drop table public.occurrences;

create table if not exists public.occurrences(
	id bigserial not null,
	delivery_id bigint not null,
	description text not null,
	date_register timestamp not null,
	constraint occurrences_pkey primary key (id),
	constraint fk_occurrences_deliveries
		foreign key (delivery_id)
		references deliveries(id)
);