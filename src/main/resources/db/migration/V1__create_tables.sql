--CREATE DATABASE cinema;

	Create table director(
	id serial primary key,
	first_name character varying(255),
	last_name character varying(255),
	birth_date date
	);

	Create table film(
	id serial primary key,
	director_id integer references Director(id),
	name character varying(255),
	release_date date,
	genre character varying(255)
	)
