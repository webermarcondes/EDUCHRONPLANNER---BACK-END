--- INSERTS NA TABELA DIA DA SEMANA
INSERT INTO dia_da_semana(id, descricao)
	    VALUES (1, 'SEGUNDA-FEIRA'),
           (2, 'TERÇA-FEIRA'),
           (3, 'QUARTA-FEIRA'),
           (4, 'QUINTA-FEIRA'),
           (5, 'SEXTA-FEIRA');


--- INSERTS NA TABELA DE DIA EXCEÇÃO
INSERT INTO public.dia_excecao(id, data, motivo)
	VALUES (1, '07/09/2024', 'Dia da Indepência do Brasil'),
	       (2, '12/10/2024', 'Dia das Crianças e Nossa Senhora Aparecida'),
	       (3, '02/11/2024', 'Finados');


--- INSERTS NA TABELA DE PROFESSOR
INSERT INTO public.professor(
	id, cpf, nome_completo, qtde_dias_de_aula, status, telefone)
	VALUES (1, '12345678900', 'Jorge Henrique da Silva Naspoli', 2, 0, '48996212844'),
	       (2, '12345688901', 'Marcelo Mazon', 2, 0, '48996212954'),
	       (3, '12345678902', 'Christine Vieira', 3, 0, '48996212844'),
           (4, '12345688903', 'Dayana Ricken', 5, 0, '48996212954'),
           (5, '12345688904', 'Jailson Torquarto', 1, 0, '48996212954'),
           (6, '12345688905', 'Rogério Cortina', 2, 0, '48997554040'),
           (7, '12345688906', 'Daniel Goulart', 1, 0, '48996508090'),
           (8, '12345688907', 'Muriel Benhardt', 3, 0, '45995709000'),
           (9, '12345688908', 'Roberto Fermino Medeiros', 3, 0, '45995709001'),
           (10, '12345688909', 'Fernando Gabriel', 2, 0, '44994700610'),
           (11, '12345688910', 'Bruno Kurzawe', 3, 0, '44994700611'),
           (12, '12345688911', 'Cledemilson dos santos', 2, 0, '44994700612'),
           (13, '12345688912', 'Roni Edson dos santos', 2, 0, '44994700613'),
           (14, '12345688913', 'Roseli Jenoveva Neto', 1, 0, '44994700614'),
           (15, '12345688914', 'Liliane Fernandes', 1, 0, '44994700615'),
           (16, '12345688915', 'Lucas Bonfante Rebelo', 2, 0, '44994700616');


--- INSERTS NA TABELA USUÁRIO
INSERT INTO public.usuario(
	id, cpf, email, nivel_permissao, nome, senha, status)
	VALUES (1, '12345678911', 'em@il', 0, 'ADMIN', 'admin', 0),
	       (2, '12345678901', 'em@iLL', 1, 'Lucas Rebelo', 'lcrb123', 0);


--- INSERTS NA TABELA DE CURSO
INSERT INTO public.curso(
	id, nome, qtde_fases, usuario_coord_id)
	VALUES (1, 'Análise e desenvolvimento de sistemas', 5, 2),
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
	VALUES (1, 76, '#00FF00', 'Engenharia de requisitos', 1),
	       (2, 76, '#800080', 'Modelagem de dados', 1),
	       (3, 152, '#964b00', 'Introdução a programação de computadores', 1),
	       (4, 40, '#56070c', 'Fundamentos de pesquisa', 1),
	       (5, 36, '#Ffa500', 'Introdução a Computação', 1),
	       (6, 76, '#1E90FF', 'Programação Orientada a Objetos', 2),
	       (7, 40, '#006400', 'Estrutura de dados', 2),
	       (8, 36, '#DAA520', 'UI/UX Design de sistema', 2),
	       (9, 76, '#8A2BE2', 'SGBD', 2),
	       (10, 76, '#FF1493', 'Análise Orientada a Objetos', 2),
	       (11, 76, '#F0E68C', 'Desenvolvimento Web', 2),
	       (12, 76, '#FF8C00', 'Cloud & ITOps', 3),
	       (13, 40, '#9933FF', 'Tecnologias e sistemas de informação gerencial', 3),
	       (14, 36, '#FF3333', 'Arquitetura de software', 3),
	       (15, 76, '#6666FF', 'Desenvolvimento Back End', 3),
	       (16, 76, '#666600', 'Qualidade e testes de software', 3),
	       (17, 76, '#330000', 'Desenvolvimento Front End', 3),
	       (18, 156, '#99004C', 'Desenvolvimento Web', 4),
	       (19, 76, '#003300', 'Desenvolvimento de sistemas embarcados', 4),
	       (20, 76, '#FF6666', 'Engenharia de software', 4),
	       (21, 36, '#003319', 'Fundamentos de projeto', 4),
	       (22, 36, '#660033', 'Codificação de segurança da informação', 4),
	       (23, 20, '#00FF80', 'Soluções para startup', 4),
	       (24, 36, '#FFB266', 'Legilação aplicada a informação', 5),
	       (25, 40, '#4C0099', 'Metodologia do trabalho cientifico', 5),
	       (26, 76, '#99FFFF', 'Topicos especiais em ADS', 5),
	       (27, 76, '#FF66B2', 'Desenvolvimento de Aplicação(Projeto)', 5),
	       (28, 76, '#6A5ACD', 'Desenvolvimento para dispositivos móveis', 5),
	       (29, 40, '#20B2AA', 'Desenvolvimento de aplicação(Sistema)', 5),
	       (30, 36, '#32CD32', 'Certificações em ADS', 5);






--- INSERTS NA TABELA DE AGENDAPROFESSOR
INSERT INTO public.agenda_professor(
	id, dia_da_semana_id, disciplina_id, professor_id)
	VALUES (1, 1, 1, 1),
	       (2, 2, 2, 2),
	       (3, 3, 3, 3),
	       (4, 4, 3, 3),
	       (5, 5, 4, 4),
	       (6, 5, 5, 5),
	       (7, 1, 6, 6),
	       (8, 2, 7, 3),
	       (9, 2, 8, 7),
	       (10, 3, 10, 8),
	       (11, 4, 9, 2),
	       (12, 5, 11, 9),
	       (13, 1, 12, 10),
	       (14, 2, 13, 8),
	       (15, 2, 14, 6),
	       (16, 3, 15, 11),
	       (17, 4, 16, 8),
	       (18, 5, 17, 1),
	       (19, 1, 18, 9),
	       (20, 2, 19, 10),
	       (21, 3, 18, 9),
	       (22, 4, 20, 7),
	       (23, 5, 21, 12),
	       (24, 5, 22, 13),
	       (25, 1, 24, 12),
	       (26, 1, 25, 4),
	       (27, 2, 26, 15),
	       (28, 3, 27, 16),
	       (29, 4, 28, 11),
	       (30, 5, 29, 11),
	       (31, 5, 30, 13);
