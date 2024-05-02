-- Table: public.dia_da_semana

-- DROP TABLE IF EXISTS public.dia_da_semana;

CREATE TABLE IF NOT EXISTS public.dia_da_semana
(
    id bigint NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT dia_da_semana_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dia_da_semana
    OWNER to postgres;