ALTER SEQUENCE IF EXISTS public.dia_da_semana_id_seq
    OWNED BY NONE;
SELECT setval('public.dia_excecao_id_seq', 7, true);

ALTER SEQUENCE IF EXISTS public.professor_id_seq
    OWNED BY NONE;
SELECT setval('public.professor_id_seq', 16, true);

ALTER SEQUENCE IF EXISTS public.usuario_id_seq
    OWNED BY NONE;
SELECT setval('public.usuario_id_seq', 2, true);

ALTER SEQUENCE IF EXISTS public.curso_id_seq
    OWNED BY NONE;
SELECT setval('public.curso_id_seq', 2, true);

ALTER SEQUENCE IF EXISTS public.fase_id_seq
    OWNED BY NONE;
SELECT setval('public.fase_id_seq', 9, true);

ALTER SEQUENCE IF EXISTS public.disciplina_id_seq
    OWNED BY NONE;
SELECT setval('public.disciplina_id_seq', 30, true);

ALTER SEQUENCE IF EXISTS public.agenda_professor_id_seq
    OWNED BY NONE;
SELECT setval('public.agenda_professor_id_seq', 30, true);









