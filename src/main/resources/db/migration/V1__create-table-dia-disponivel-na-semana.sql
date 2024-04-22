-- Table: public.dia_disponivel_na_semana

-- DROP TABLE IF EXISTS public.dia_disponivel_na_semana;

CREATE TABLE IF NOT EXISTS public.dia_disponivel_na_semana
(
    id bigint NOT NULL,
    dia_da_semana character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT dia_disponivel_na_semana_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dia_disponivel_na_semana
    OWNER to postgres;