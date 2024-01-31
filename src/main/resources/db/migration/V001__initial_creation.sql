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


CREATE TABLE public.state (
	id bigserial NOT NULL,
	"name" varchar(25) NOT NULL,
	CONSTRAINT state_pkey PRIMARY KEY (id),
	CONSTRAINT state_name_unique UNIQUE (name)
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
	CONSTRAINT fk_state_id FOREIGN KEY (state_id) REFERENCES public.state(id)
);


CREATE TABLE public."permission" (
	id bigserial NOT NULL,
	description varchar(50) NOT NULL,
	"name" varchar(30) NOT NULL,
	CONSTRAINT permission_pkey PRIMARY KEY (id)
);


CREATE TABLE public.group_permission (
	groups_id int8 NOT NULL,
	permission_id int8 NOT NULL,
	CONSTRAINT fk_group_id FOREIGN KEY (groups_id) REFERENCES public."groups"(id),
	CONSTRAINT fk_permission_id FOREIGN KEY (permission_id) REFERENCES public."permission"(id)
);


CREATE TABLE public.user_group (
	users_id int8 NOT NULL,
	group_id int8 NOT NULL,
	CONSTRAINT fk_group_id FOREIGN KEY (group_id) REFERENCES public."groups"(id),
	CONSTRAINT fk_user_id FOREIGN KEY (users_id) REFERENCES public.users(id)
);


CREATE TABLE public.address (
	id bigserial NOT NULL,
	complement varchar(255) NULL,
	neighborhood varchar(255) NULL,
	"number" varchar(255) NULL,
	street varchar(255) NULL,
	zip_code varchar(255) NULL,
	address_city_id int8 NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id),
	CONSTRAINT fk_address_city_id FOREIGN KEY (address_city_id) REFERENCES public.city(id)
);


CREATE TABLE public.restaurant (
	id bigserial NOT NULL,
	delivery_fee numeric(19, 2) NOT NULL,
	"name" varchar(50) NOT NULL,
	address_id int8 NOT NULL,
	kitchen_id int8 NOT NULL,
	CONSTRAINT restaurant_pkey PRIMARY KEY (id),
	CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES public.address(id),
	CONSTRAINT fk_kitchen_id FOREIGN KEY (kitchen_id) REFERENCES public.kitchen(id)
);


CREATE TABLE public.restaurant_payment_type (
	restaurant_id int8 NOT NULL,
	payment_type_id int8 NOT NULL,
	CONSTRAINT fk_payment_type_id FOREIGN KEY (payment_type_id) REFERENCES public.payment_type(id),
	CONSTRAINT fk_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id)
);


CREATE TABLE public.orders (
	id bigserial NOT NULL,
	cancellation_date timestamp NULL,
	confirmation_date timestamp NULL,
	delivery_date timestamp NULL,
	delivery_fee numeric(19, 2) NULL,
	gross_amount numeric(19, 2) NULL,
	net_amount numeric(19, 2) NULL,
	order_status int4 NULL,
	quantity int4 NULL,
	address_id int8 NOT NULL,
	payment_type_id int8 NOT NULL,
	restaurant_id int8 NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id),
	CONSTRAINT fk_payment_type_id FOREIGN KEY (payment_type_id) REFERENCES public.payment_type(id),
	CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES public.address(id),
	CONSTRAINT fk_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id)
);


CREATE TABLE public.product (
	id bigserial NOT NULL,
	description varchar(255) NOT NULL,
	is_active bool NOT NULL,
	"name" varchar(50) NOT NULL,
	price numeric(19, 2) NOT NULL,
	restaurant_id int8 NOT NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id),
	CONSTRAINT fk_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id)
);


CREATE TABLE public.order_product (
	id bigserial NOT NULL,
	observation varchar(255) NULL,
	quantity int4 NULL,
	total_value numeric(19, 2) NULL,
	unitary_value numeric(19, 2) NULL,
	order_id int8 NOT NULL,
	product_id int8 NOT NULL,
	CONSTRAINT order_product_pkey PRIMARY KEY (id),
	CONSTRAINT fk_order_product_id FOREIGN KEY (product_id) REFERENCES public.product(id),
	CONSTRAINT fk_order_order_id FOREIGN KEY (order_id) REFERENCES public.orders(id)
);