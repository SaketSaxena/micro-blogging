CREATE ROLE micro-blogging WITH LOGIN PASSWORD 'micro-blogging';

CREATE DATABASE "micro-blogging" WITH OWNER = "micro-blogging"

CREATE TABLE IF NOT EXISTS public.messages
(
    id bigint NOT NULL DEFAULT nextval('messages_id_seq'::regclass),
    author character varying(250) COLLATE pg_catalog."default" NOT NULL,
    message text COLLATE pg_catalog."default" NOT NULL,
    source character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT messages_pkey PRIMARY KEY (id)
)