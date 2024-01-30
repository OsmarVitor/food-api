CREATE TABLE public."groups" (
	id bigserial NOT NULL,
	"name" varchar(30) NULL,
	CONSTRAINT groups_pkey PRIMARY KEY (id)
);


CREATE TABLE public.kitchen (
	id bigserial NOT NULL,
	"name" varchar(30) NOT NULL,
	CONSTRAINT kitchen_pkey PRIMARY KEY (id)
);


CREATE TABLE public.payment_type (
	id bigserial NOT NULL,
	description varchar(50) NOT NULL,
	CONSTRAINT payment_type_pkey PRIMARY KEY (id)
);


CREATE TABLE public."permission" (
	id bigserial NOT NULL,
	description varchar(50) NOT NULL,
	"name" varchar(30) NOT NULL,
	CONSTRAINT permission_pkey PRIMARY KEY (id)
);


CREATE TABLE public.state (
	id bigserial NOT NULL,
	"name" varchar(25) NOT NULL,
	CONSTRAINT state_pkey PRIMARY KEY (id),
	CONSTRAINT uk_2g0hi7w44i4sjkffh61pusaav UNIQUE (name)
);


CREATE TABLE public.users (
	id bigserial NOT NULL,
	email varchar(100) NOT NULL,
	"name" varchar(150) NOT NULL,
	"password" varchar(50) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);


CREATE TABLE public.city (
	id bigserial NOT NULL,
	"name" varchar(50) NOT NULL,
	state_id int8 NOT NULL,
	CONSTRAINT city_pkey PRIMARY KEY (id),
	CONSTRAINT fk6p2u50v8fg2y0js6djc6xanit FOREIGN KEY (state_id) REFERENCES public.state(id)
);


CREATE TABLE public.group_permission (
	groups_id int8 NOT NULL,
	permission_id int8 NOT NULL,
	CONSTRAINT fk8jfrgi2mrda23acrad626w74q FOREIGN KEY (groups_id) REFERENCES public."groups"(id),
	CONSTRAINT fkss14p30qbokhpkpdov4ha3wro FOREIGN KEY (permission_id) REFERENCES public."permission"(id)
);


CREATE TABLE public.restaurant (
	id bigserial NOT NULL,
	complement varchar(30) NULL,
	neighborhood varchar(30) NULL,
	"number" varchar(10) NULL,
	street varchar(50) NULL,
	zip_code varchar(10) NULL,
	delivery_fee numeric(19, 2) NOT NULL,
	"name" varchar(50) NOT NULL,
	address_city_id int8 NULL,
	kitchen_id int8 NOT NULL,
	CONSTRAINT restaurant_pkey PRIMARY KEY (id),
	CONSTRAINT fk8pcwgn41lfg43d8u82ewn3cn FOREIGN KEY (address_city_id) REFERENCES public.city(id),
	CONSTRAINT fkrur1dqx79jim8s8dvdp16qc3d FOREIGN KEY (kitchen_id) REFERENCES public.kitchen(id)
);


CREATE TABLE public.restaurant_payment_type (
	restaurant_id int8 NOT NULL,
	payment_type_id int8 NOT NULL,
	CONSTRAINT fk36v769cr8ec61p38tcllgmiul FOREIGN KEY (payment_type_id) REFERENCES public.payment_type(id),
	CONSTRAINT fkk8tfvxtsqptpfev7enmv0pby4 FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id)
);


CREATE TABLE public.user_group (
	users_id int8 NOT NULL,
	group_id int8 NOT NULL,
	CONSTRAINT fkbegtgnl3oq004958pisko4fu4 FOREIGN KEY (group_id) REFERENCES public."groups"(id),
	CONSTRAINT fkg3bkro9p89yr5uestuf2n2vh9 FOREIGN KEY (users_id) REFERENCES public.users(id)
);


CREATE TABLE public.product (
	id bigserial NOT NULL,
	description varchar(255) NOT NULL,
	is_active bool NOT NULL,
	"name" varchar(50) NOT NULL,
	price numeric(19, 2) NOT NULL,
	restaurant_id int8 NOT NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id),
	CONSTRAINT fkp4b7e36gck7975p51raud8juk FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id)
);