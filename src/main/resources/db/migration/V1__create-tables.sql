-- Table: public.dia_da_semana
-- DROP TABLE IF EXISTS public.dia_da_semana;
CREATE TABLE IF NOT EXISTS public.dia_da_semana
(
    id SERIAL NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT dia_da_semana_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dia_da_semana
    OWNER to postgres;



-- Table: public.dia_excecao
-- DROP TABLE IF EXISTS public.dia_excecao;
CREATE TABLE IF NOT EXISTS public.dia_excecao
(
    id SERIAL NOT NULL,
    data character varying(255) COLLATE pg_catalog."default",
    motivo character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT dia_excecao_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dia_excecao
    OWNER to postgres;




-- Table: public.sala
-- DROP TABLE IF EXISTS public.sala;
CREATE TABLE IF NOT EXISTS public.sala
(
    id SERIAL NOT NULL,
    numero integer,
    CONSTRAINT sala_pkey PRIMARY KEY (id),
    CONSTRAINT uk_rld8ojxb8ymcif6q6060vq924 UNIQUE (numero)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.sala
    OWNER to postgres;




-- Table: public.professor
-- DROP TABLE IF EXISTS public.professor;
CREATE TABLE IF NOT EXISTS public.professor
(
    id SERIAL NOT NULL,
    cpf character varying(11) COLLATE pg_catalog."default",
    nome_completo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    qtde_dias_de_aula integer,
    status smallint,
    telefone character varying(255) COLLATE pg_catalog."default",
    url_foto_perfil character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT professor_pkey PRIMARY KEY (id),
    CONSTRAINT uk_pk1omryj5cud6uslkepgyfrca UNIQUE (cpf),
    CONSTRAINT professor_status_check CHECK (status >= 0 AND status <= 1)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.professor
    OWNER to postgres;



-- Table: public.usuario
-- DROP TABLE IF EXISTS public.usuario;
CREATE TABLE IF NOT EXISTS public.usuario
(
    id SERIAL NOT NULL,
    cpf character varying(11) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    nivel_permissao smallint,
    nome character varying(255) COLLATE pg_catalog."default",
    senha character varying(8),
    status smallint,
    url_foto_perfil character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT uk_692bsnqxa8m9fmx7m1yc6hsui UNIQUE (cpf),
    CONSTRAINT usuario_nivel_permissao_check CHECK (nivel_permissao >= 0 AND nivel_permissao <= 1),
    CONSTRAINT usuario_status_check CHECK (status >= 0 AND status <= 1)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario
    OWNER to postgres;


-- Table: public.curso
-- DROP TABLE IF EXISTS public.curso;
CREATE TABLE IF NOT EXISTS public.curso
(
    id SERIAL NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default",
    qtde_fases integer,
    usuario_coord_id bigint,
    CONSTRAINT curso_pkey PRIMARY KEY (id),
    CONSTRAINT fk4xdafkryf5dr1qsm20ng5sqcw FOREIGN KEY (usuario_coord_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.curso
    OWNER to postgres;


-- Table: public.fase
-- DROP TABLE IF EXISTS public.fase;
CREATE TABLE IF NOT EXISTS public.fase
(
    id SERIAL NOT NULL,
    numero integer,
    curso_id bigint,
    CONSTRAINT fase_pkey PRIMARY KEY (id),
    CONSTRAINT fk6e9xw486mlxx6f775yo2kmule FOREIGN KEY (curso_id)
        REFERENCES public.curso (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.fase
    OWNER to postgres;


-- Table: public.disciplina
-- DROP TABLE IF EXISTS public.disciplina;
CREATE TABLE IF NOT EXISTS public.disciplina
(
    id SERIAL NOT NULL,
    carga_horaria integer,
    codigo_cor character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    fase_id bigint,
    CONSTRAINT disciplina_pkey PRIMARY KEY (id),
    CONSTRAINT fksjv5fwaly985vni2htkjwf9el FOREIGN KEY (fase_id)
        REFERENCES public.fase (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.disciplina
    OWNER to postgres;


-- Table: public.agenda_professor
-- DROP TABLE IF EXISTS public.agenda_professor;
CREATE TABLE IF NOT EXISTS public.agenda_professor
(
    id SERIAL NOT NULL,
    dia_da_semana_id bigint NOT NULL,
    disciplina_id bigint,
    professor_id bigint,
    sala_id bigint,
    CONSTRAINT agenda_professor_pkey PRIMARY KEY (id),
    CONSTRAINT fk85j11vx5bs6j3umaangu5tbg7 FOREIGN KEY (sala_id)
        REFERENCES public.sala (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fklw88vq6u303avg4ucbbeks2ow FOREIGN KEY (dia_da_semana_id)
        REFERENCES public.dia_da_semana (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fks2m3lpvpf9gihby1a0lxppo1r FOREIGN KEY (disciplina_id)
        REFERENCES public.disciplina (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fksirw4ffoha20voisbfnc638bs FOREIGN KEY (professor_id)
        REFERENCES public.professor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.agenda_professor
    OWNER to postgres;