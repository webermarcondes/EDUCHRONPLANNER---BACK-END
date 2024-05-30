--- INSERTS NA TABELA DIA DA SEMANA
INSERT INTO dia_da_semana(id, descricao)
	    VALUES (1, 'SEGUNDA-FEIRA'),
           (2, 'TERÇA-FEIRA'),
           (3, 'QUARTA-FEIRA'),
           (4, 'QUINTA-FEIRA'),
           (5, 'SEXTA-FEIRA'),
           (6, 'SÁBADO');


--- INSERTS NA TABELA DE DIA EXCEÇÃO
INSERT INTO public.dia_excecao(id, data, motivo)
	VALUES (1, '07/09/2024', 'Dia da Indepência do Brasil'),
	       (2, '12/10/2024', 'Dia das Crianças e Nossa Senhora Aparecida'),
	       (3, '02/11/2024', 'Finados');


--- INSERTS NA TABELA DE SALA
INSERT INTO public.sala(id, numero)
	VALUES (1, 104),
	       (2, 106);

--- INSERTS NA TABELA DE PROFESSOR
INSERT INTO public.professor(
	id, cpf, nome_completo, qtde_dias_de_aula, status, telefone, url_foto_perfil)
	VALUES (1, '12345678900', 'Antonio', 3, 0, '48996212844', 'url'),
	       (2, '12345688900', 'Marcos', 3, 0, '48996212954', 'url2');


--- INSERTS NA TABELA USUÁRIO
INSERT INTO public.usuario(
	id, cpf, email, nivel_permissao, nome, senha, status, url_foto_perfil)
	VALUES (1, '12345678911', 'em@il', 0, 'ADMIN', 'admin', 0, 'url'),
	       (2, '12345678901', 'em@iLL', 1, 'Lucas Rebelo', 'lcrb123', 0, 'url1');


--- INSERTS NA TABELA DE CURSO
INSERT INTO public.curso(
	id, nome, qtde_fases, usuario_coord_id)
	VALUES (1, 'Técnologo em Análise e desenvolvimento de sistemas', 5, 2),
	       (2, 'Técnologia em Gestão da Informação', 4, 2);

--- INSERTS NA TABELA DE FASE
INSERT INTO public.fase(
	id, numero, curso_id)
	VALUES (1, 1, 1),
	       (2, 2, 1),
	       (3, 3, 1),
	       (4, 4, 1),
	       (5, 5, 1),
	       (6, 1, 2),
           (7, 2, 2),
           (8, 3, 2),
           (9, 4, 2);

--- INSERTS NA TABELA DE DISCIPLINA
INSERT INTO public.disciplina(
	id, carga_horaria, codigo_cor, nome, fase_id)
	VALUES (1, 40, '#ffff', 'Fundamentos de pesquisa', 1),
	       (2, 152, '#fff3', 'Introdução a Programação', 2);


--- INSERTS NA TABELA DE AGENDAPROFESSOR
INSERT INTO public.agenda_professor(
	id, dia_da_semana_id, disciplina_id, professor_id, sala_id)
	VALUES (1, 1, 1, 1, 1),
	        (2, 2, 2, 2, 2);
