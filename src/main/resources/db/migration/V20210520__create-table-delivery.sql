-- drop table

-- drop table public.deliveries;

create table if not exists public.deliveries (
	id bigserial not null,
	client_id int8 not null,
	tax numeric(10,2) not null,
	status varchar(20) not null,
	date_order timestamp not null,
	date_finished timestamp null,
	recipient_name varchar(60) not null,
	recipient_adress varchar(255) not null,
	recipient_number varchar(30) not null,
	recipient_complement varchar(60),
	recipient_neighborhood varchar(30) not null,
	constraint deliveries_pkey primary key (id),
	constraint fk_deliveries_client
      foreign key(client_id) 
	  references clients(id)
);