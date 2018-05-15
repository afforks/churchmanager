
-- INSERINDO PERFIL
INSERT INTO perfil (id, status, versao, descricao, nome) VALUES (1, 'ATIVO', 0, 'ADMINISTRADOR', 'ADMINISTRADOR');

-- INSERINFO USUÁRIO
INSERT INTO usuario (id, status, versao, senha, perfil_id, nome_completo, email) VALUES (1, 'ATIVO', 0, '202cb962ac59075b964b07152d234b70', 1, 'Usuário administrador', 'user@admin');

-- INSERINDO PÁGINAS
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (1,'ATIVO',0,'Descrição','Cadastrar Página','CADASTRAR_PAGINA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (2,'ATIVO',0,'Descrição','Editar Página','EDITAR_PAGINA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (3,'ATIVO',0,'Descrição','Listar Página','LISTAR_PAGINA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (4,'ATIVO',0,'Descrição','Cadastrar Perfil','CADASTRAR_PERFIL');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (5,'ATIVO',0,'Descrição','Editar Perfil','EDITAR_PERFIL');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (6,'ATIVO',0,'Descrição','Listar Perfil','LISTAR_PERFIL');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (7,'ATIVO',0,'Descrição','Cadastrar Usuário','CADASTRAR_USUARIO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (8,'ATIVO',0,'Descrição','Editar Usuário','EDITAR_USUARIO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (9,'ATIVO',0,'Descrição','Listar Usuário','LISTAR_USUARIO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (10,'ATIVO',0,'Descrição','Cadastrar Pessoa','CADASTRAR_PESSOA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (11,'ATIVO',0,'Descrição','Editar Pessoa','EDITAR_PESSOA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (12,'ATIVO',0,'Descrição','Listar Pessoa','LISTAR_PESSOA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (13,'ATIVO',0,'Descrição','Cadastrar Atividade Eclesiástica','CADASTRAR_ATIVIDADE_ECLESIASTICA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (14,'ATIVO',0,'Descrição','Editar Atividade Eclesiástica','EDITAR_ATIVIDADE_ECLESIASTICA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (15,'ATIVO',0,'Descrição','Listar Atividade Eclesiástica','LISTAR_ATIVIDADE_ECLESIASTICA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (16,'ATIVO',0,'Descrição','Cadastrar Categoria Movimentação','CADASTRAR_CATEGORIA_MOVIMENTACAO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (17,'ATIVO',0,'Descrição','Editar Categoria Movimentação','EDITAR_CATEGORIA_MOVIMENTACAO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (18,'ATIVO',0,'Descrição','Listar Categoria Movimentação','LISTAR_CATEGORIA_MOVIMENTACAO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (19,'ATIVO',0,'Descrição','Cadastrar Movimentação','CADASTRAR_MOVIMENTACAO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (20,'ATIVO',0,'Descrição','Editar Movimentação','EDITAR_MOVIMENTACAO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (21,'ATIVO',0,'Descrição','Listar Movimentação','LISTAR_MOVIMENTACAO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (22,'ATIVO',0,'Descrição','Cadastrar Evento','CADASTRAR_EVENTO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (23,'ATIVO',0,'Descrição','Editar Evento','EDITAR_EVENTO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (24,'ATIVO',0,'Descrição','Listar Evento','LISTAR_EVENTO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (25,'ATIVO',0,'Cadastrar patrimônio','Cadastrar patrimônio','CADASTRAR_PATRIMONIO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (26,'ATIVO',0,'patrimônio','Editar patrimônio','EDITAR_PATRIMONIO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (27,'ATIVO',0,'Listar patrimônio','Listar patrimônio','LISTAR_PATRIMONIO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (28,'ATIVO',0,'Cadastrar Dízimo','Cadastrar Dízimo','CADASTRAR_DIZIMO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (29,'ATIVO',0,'Editar Dízimo','Editar Dízimo','EDITAR_DIZIMO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (30,'ATIVO',0,'Listar Dízimo','Listar Dízimo','LISTAR_DIZIMO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (31,'ATIVO',0,'Cadastrar cargo','Cadastrar cargo','CADASTRAR_CARGO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (32,'ATIVO',0,'Editar cargo','Editar cargo','EDITAR_CARGO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (33,'ATIVO',0,'Listar cargo','Listar cargo','LISTAR_CARGO');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (34,'ATIVO',0,'Cadastrar Diretoria','Cadastrar Diretoria','CADASTRAR_DIRETORIA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (35,'ATIVO',0,'Editar Diretoria','Editar Diretoria','EDITAR_DIRETORIA');
INSERT INTO pagina (id, status, versao, descricao, nome, nome_identificador) VALUES (36,'ATIVO',0,'Listar Diretoria','Listar Diretoria','LISTAR_DIRETORIA');


-- RELACIONANDO USUÁRIO E PÁGINAS
INSERT INTO usuario_pagina (usuario_id, pagina_id) VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36);
 

-- RELACIONANDO PERFIL E PÁGINAS
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	1	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	2	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	3	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	4	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	5	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	6	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	7	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	8	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	9	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	10	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	11	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	12	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	13	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	14	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	15	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	16	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	17	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	18	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	19	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	20	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	21	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	22	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	23	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	24	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	25	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	26	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	27	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	28	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	29	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	30	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	31	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	32	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	33	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	34	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	35	);
INSERT INTO perfil_pagina(perfil_id, pagina_id) VALUES (1,	36	);


-- INSERINDO ATIVIDADES ECLESIASTICAS
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (1, 'ATIVO', 0, 'Cantar', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (2, 'ATIVO', 0, 'Pregar', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (3, 'ATIVO', 0, 'Ministrar aulas', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (4, 'ATIVO', 0, 'Ministrar cursos', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (5, 'ATIVO', 0, 'Ministrar palestras', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (6, 'ATIVO', 0, 'Tocar guitarra', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (7, 'ATIVO', 0, 'Tocar violão', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (8, 'ATIVO', 0, 'Tocar teclado', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (9, 'ATIVO', 0, 'Tocar contra baixo', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (10, 'ATIVO', 0, 'Tocar bateria', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (11, 'ATIVO', 0, 'Recepcionar', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (12, 'ATIVO', 0, 'Coordenar cultos', 'Descrição da atividade');

-- INSERINDO PESSOAS 
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	1	, '20180101101010001', 'ATIVO'	, 0,	'Apelido1'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-01'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'José da Glória'			,	'Observação'	,	'Profissão'	,	'M'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	2	, '20180101101010002', 'ATIVO'	, 0,	'Apelido2'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-02'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Maria da Glória'			,	'Observação'	,	'Profissão'	,	'F'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	3	, '20180101101010003', 'ATIVO'	, 0,	'Apelido3'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-02'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'João da Glória'			,	'Observação'	,	'Profissão'	,	'M'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	4	, '20180101101010004', 'ATIVO'	, 0,	'Apelido4'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-02'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Neide Lima'				,	'Observação'	,	'Profissão'	,	'F'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	5	, '20180101101010005', 'ATIVO'	, 0,	'Apelido5'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-03'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Wilton Aureliano'			,	'Observação'	,	'Profissão'	,	'M'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	6	, '20180101101010006', 'ATIVO'	, 0,	'Apelido6'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-03'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Aline Mysna'				,	'Observação'	,	'Profissão'	,	'F'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	7	, '20180101101010007', 'ATIVO'	, 0,	'Apelido7'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-03'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Alex Wilton'				,	'Observação'	,	'Profissão'	,	'M'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	8	, '20180101101010008', 'ATIVO'	, 0,	'Apelido8'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-04'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Amanda Miller'				,	'Observação'	,	'Profissão'	,	'F'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	9	, '20180101101010009', 'ATIVO'	, 0,	'Apelido9'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-04'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Junior Oliveira'			,	'Observação'	,	'Profissão'	,	'M'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	10	, '20180101101010010', 'ATIVO'	, 0,	'Apelido10'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-05'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Maria José Calvalcante'	,	'Observação'	,	'Profissão'	,	'F'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	11	, '20180101101010011', 'ATIVO'	, 0,	'Apelido11'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-05'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Francisco Alves'			,	'Observação'	,	'Profissão'	,	'M');
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	12	, '20180101101010012', 'ATIVO'	, 0,	'Apelido12'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-06'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Sandra Maria'				,	'Observação'	,	'Profissão'	,	'F'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	13	, '20180101101010013', 'ATIVO'	, 0,	'Apelido13'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-06'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Ian Lucas'					,	'Observação'	,	'Profissão'	,	'M'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	14	, '20180101101010014', 'ATIVO'	, 0,	'Apelido14'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-07'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Héllen Súzany'				,	'Observação'	,	'Profissão'	,	'F'	);
INSERT INTO pessoa (id, matricula, status, versao, apelido, data_cadastro, data_batismo, data_conversao, data_nascimento, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo)  VALUES (	15	, '20180101101010015', 'ATIVO'	, 0,	'Apelido15'		,	 '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-07'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'David Emanuell'			,	'Observação'	,	'Profissão'	,	'M'); 

-- RELACIONANDO PESSOAS E ATIVIDADES
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

/* CARGOS */
INSERT INTO cargo (id, status, versao, nome, descricao) VALUES (1, 'ATIVO', 0, 'Presidente', 'Descrição do cargo');
INSERT INTO cargo (id, status, versao, nome, descricao) VALUES (2, 'ATIVO', 0, 'Tesoureiro', 'Descrição do cargo');
INSERT INTO cargo (id, status, versao, nome, descricao) VALUES (3, 'ATIVO', 0, 'Secretário', 'Descrição do cargo');
INSERT INTO cargo (id, status, versao, nome, descricao) VALUES (4, 'ATIVO', 0, 'Diretor de patrimônio', 'Descrição do cargo');
INSERT INTO cargo (id, status, versao, nome, descricao) VALUES (5, 'ATIVO', 0, 'Vice-presidente', 'Descrição do cargo');

