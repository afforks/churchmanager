
-- INSERINDO PERFIL
INSERT INTO perfil (id, status, versao, descricao, nome) VALUES (1, 'ATIVO', 0, 'ADMINISTRADOR', 'ADMINISTRADOR');

-- INSERINFO USUÁRIO
INSERT INTO usuario (id, status, versao, senha, perfil_id, nome_completo, email) VALUES (1, 'ATIVO', 0, '202cb962ac59075b964b07152d234b70', 1, 'Usuário administrador', 'user@admin');

-- INSERINDO PÁGINAS
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (1, 'ATIVO', 0, 'Cadastrar Página', 'CADASTRAR_PAGINA', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (2, 'ATIVO', 0, 'Editar Página', 'EDITAR_PAGINA', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (3, 'ATIVO', 0, 'Listar Página', 'LISTAR_PAGINA', 'Descrição');

INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (4, 'ATIVO', 0, 'Cadastrar Perfil', 'CADASTRAR_PERFIL', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (5, 'ATIVO', 0, 'Editar Perfil', 'EDITAR_PERFIL', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (6, 'ATIVO', 0, 'Listar Perfil', 'LISTAR_PERFIL', 'Descrição');

INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (7, 'ATIVO', 0, 'Cadastrar Usuário', 'CADASTRAR_USUARIO', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (8, 'ATIVO', 0, 'Editar Usuário', 'EDITAR_USUARIO', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (9, 'ATIVO', 0, 'Listar Usuário', 'LISTAR_USUARIO', 'Descrição');

--

INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (10, 'ATIVO', 0, 'Cadastrar Pessoa', 'CADASTRAR_PESSOA', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (11, 'ATIVO', 0, 'Editar Pessoa', 'EDITAR_PESSOA', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (12, 'ATIVO', 0, 'Listar Pessoa', 'LISTAR_PESSOA', 'Descrição');

INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (13, 'ATIVO', 0, 'Cadastrar Atividade Eclesiástica', 'CADASTRAR_ATIVIDADE_ECLESIASTICA', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (14, 'ATIVO', 0, 'Editar Atividade Eclesiástica', 'EDITAR_ATIVIDADE_ECLESIASTICA', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (15, 'ATIVO', 0, 'Listar Atividade Eclesiástica', 'LISTAR_ATIVIDADE_ECLESIASTICA', 'Descrição');



INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (16, 'ATIVO', 0, 'Cadastrar Categoria Movimentação', 'CADASTRAR_CATEGORIA_MOVIMENTACAO', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (17, 'ATIVO', 0, 'Editar Categoria Movimentação', 'EDITAR_CATEGORIA_MOVIMENTACAO', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (18, 'ATIVO', 0, 'Listar Categoria Movimentação', 'LISTAR_CATEGORIA_MOVIMENTACAO', 'Descrição');

INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (19, 'ATIVO', 0, 'Cadastrar Movimentação', 'CADASTRAR_MOVIMENTACAO', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (20, 'ATIVO', 0, 'Editar Movimentação', 'EDITAR_MOVIMENTACAO', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (21, 'ATIVO', 0, 'Listar Movimentação', 'LISTAR_MOVIMENTACAO', 'Descrição');

INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (22, 'ATIVO', 0, 'Cadastrar Evento', 'CADASTRAR_EVENTO', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (23, 'ATIVO', 0, 'Editar Evento', 'EDITAR_EVENTO', 'Descrição');
INSERT INTO pagina (id, status, versao, nome, nome_identificador, descricao) VALUES (24, 'ATIVO', 0, 'Listar Evento', 'LISTAR_EVENTO', 'Descrição');


-- RELACIONANDO USUÁRIO E PÁGINAS
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	1	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	2	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	3	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	4	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	5	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	6	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	7	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	8	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	9	);

--

INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	10	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	11	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	12	);

INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	13	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	14	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	15	);

INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	16	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	17	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	18	);

INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	19	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	20	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	21	);

INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	22	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	23	);
INSERT INTO usuario_pagina(usuario_id, pagina_id) VALUES (1,	24	);


--

INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (1, 'ATIVO', 0, 'Cantar', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (2, 'ATIVO', 0, 'Pregar', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (3, 'ATIVO', 0, 'Ministrar aulas', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (4, 'ATIVO', 0, 'Ministrar cursos', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (5, 'ATIVO', 0, 'Ministrar palestras', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (6, 'ATIVO', 0, 'Tocar guitarra', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (7, 'ATIVO', 0, 'Tocar violão', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (8, 'ATIVO', 0, 'Tocar teclado', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (9, 'ATIVO', 0, 'Tocar baixo', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (10, 'ATIVO', 0, 'Tocar bateria', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (11, 'ATIVO', 0, 'Recepcionar', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (12, 'ATIVO', 0, 'Coordenar cultos', 'Descrição da atividade');

INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	1	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-8888'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-01'	,	'asd@mail.com'	,	'asd2@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'João da Glória'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7898-6545'	,	'(88)98654-6544'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	2	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-02'	,	'asd1@mail.com'	,	'asd21@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Maria da Glória'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-3111'	,	'(88)94554-1111'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	3	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-02'	,	'asd2@mail.com'	,	'asd22@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'João da Glória'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7898-1311'	,	'(88)98656-1611'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	4	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-02'	,	'asd3@mail.com'	,	'asd23@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Neide Lima'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-1141'	,	'(88)98254-1121'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	5	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-03'	,	'asd4@mail.com'	,	'asd24@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Wilton Aureliano'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)5858-1111'	,	'(88)93654-1131'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	6	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-03'	,	'asd5@mail.com'	,	'asd25@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Aline Mysna'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7698-1151'	,	'(88)98644-1141'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	7	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-03'	,	'asd6@mail.com'	,	'asd26@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Alex Wilton'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7898-1177'	,	'(88)95654-1151'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	8	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-04'	,	'asd7@mail.com'	,	'asd27@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Amanda Miller'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-1881'	,	'(88)98654-1161'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	9	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-04'	,	'asd8@mail.com'	,	'asd28@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Junior Oliveira'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7838-1441'	,	'(88)98674-7711'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	10	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-05'	,	'asd9@mail.com'	,	'asd29@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Maria José Calvalcante'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7848-1114'	,	'(88)98854-1881'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	11	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-05'	,	'asd10@mail.com'	,	'asd20@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Francisco Alves'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7838-1131'	,	'(88)98694-1991'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	12	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-06'	,	'asd11@mail.com'	,	'asd211@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Sandra Maria'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-1123'	,	'(88)98054-1101'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	13	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-06'	,	'asd12@mail.com'	,	'asd212@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Ian Lucas'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7838-1111'	,	'(88)92154-1211'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	14	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-07'	,	'asd13@mail.com'	,	'asd213@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Héllen Suéllen'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-1123'	,	'(88)32654-1321'	);
INSERT INTO pessoa (id, status, versao, apelido, celular, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	15	,	'ATIVO'	, 0,	'Apelido'		,	'(88)99999-1111'	,	'2006-12-05'	,	'2006-12-05'	,	'1992-01-07'	,	'asd14@mail.com'	,	'asd214@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'David Emanuell'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7898-1131'	,	'(88)98434-1431'	); 
   
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (1, 1);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (1, 2);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (2, 3);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (3, 4);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (4, 5);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (4, 6);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (5, 7);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (5, 8);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (6, 9);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (6, 8);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (7, 7);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (7, 6);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (8, 5);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (8, 4);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (9, 3);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (9, 2);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (10, 1);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (10, 2);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (11, 3);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (11, 4);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (12, 5);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (12, 6);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (13, 7);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (13, 8);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (14, 9);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (14, 10);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (15, 11);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (15, 12);
insert into pessoa_atividade_eclesiastica (pessoa_id, atividade_eclesiastica_id) values (15, 9);

--
/* INSERINDO CATEGORIAS */
INSERT INTO categoria_movimentacao (id, status, versao, nome, descricao) VALUES (1, 'ATIVO', 0, 'Dízimo', 'Descrição da categoria');
INSERT INTO categoria_movimentacao (id, status, versao, nome, descricao) VALUES (2, 'ATIVO', 0, 'Oferta', 'Descrição da categoria');
INSERT INTO categoria_movimentacao (id, status, versao, nome, descricao) VALUES (3, 'ATIVO', 0, 'Combustível', 'Descrição da categoria');
INSERT INTO categoria_movimentacao (id, status, versao, nome, descricao) VALUES (4, 'ATIVO', 0, 'Doação', 'Descrição da categoria');

/* EVENTOS */
INSERT INTO evento (id, status, versao, descricao, nome, dia, mes) VALUES (1, 'ATIVO', 0, 'Descrição do evento...', 'VINDE', '02', '08');
INSERT INTO evento (id, status, versao, descricao, nome, dia, mes) VALUES (2, 'ATIVO', 0, 'Descrição do evento...', 'Filho pródigo', '02', '08');
INSERT INTO evento (id, status, versao, descricao, nome, dia, mes) VALUES (3, 'ATIVO', 0, 'Descrição do evento...', 'Impacto', '07', '09');
 