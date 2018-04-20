
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
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (9, 'ATIVO', 0, 'Tocar baixo', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (10, 'ATIVO', 0, 'Tocar bateria', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (11, 'ATIVO', 0, 'Recepcionar', 'Descrição da atividade');
INSERT INTO atividade_eclesiastica (id, status, versao, nome, descricao) VALUES (12, 'ATIVO', 0, 'Coordenar cultos', 'Descrição da atividade');

-- INSERINDO PESSOAS 
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	1	, 'c4ca4238a0b923820dcc509a6f75849b', 'ATIVO'	, 0,	'Apelido1'		,	'(88)99991-8888'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-01'	,	'asd@mail.com'	,	'asd2@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'José da Glória'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7898-6545'	,	'(88)98654-6544'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	2	, 'c81e728d9d4c2f636f067f89cc14862c', 'ATIVO'	, 0,	'Apelido2'		,	'(88)99992-1111'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-02'	,	'asd1@mail.com'	,	'asd21@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Maria da Glória'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-3111'	,	'(88)94554-1111'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	3	, 'eccbc87e4b5ce2fe28308fd9f2a7baf3', 'ATIVO'	, 0,	'Apelido3'		,	'(88)99993-1111'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-02'	,	'asd2@mail.com'	,	'asd22@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'João da Glória'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7898-1311'	,	'(88)98656-1611'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	4	, 'a87ff679a2f3e71d9181a67b7542122c', 'ATIVO'	, 0,	'Apelido4'		,	'(88)99994-1111'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-02'	,	'asd3@mail.com'	,	'asd23@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Neide Lima'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-1141'	,	'(88)98254-1121'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	5	, 'e4da3b7fbbce2345d7772b0674a318d5', 'ATIVO'	, 0,	'Apelido5'		,	'(88)99995-1111'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-03'	,	'asd4@mail.com'	,	'asd24@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Wilton Aureliano'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)5858-1111'	,	'(88)93654-1131'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	6	, '1679091c5a880faf6fb5e6087eb1b2dc', 'ATIVO'	, 0,	'Apelido6'		,	'(88)99996-1111'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-03'	,	'asd5@mail.com'	,	'asd25@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Aline Mysna'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7698-1151'	,	'(88)98644-1141'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	7	, '8f14e45fceea167a5a36dedd4bea2543', 'ATIVO'	, 0,	'Apelido7'		,	'(88)99997-1111'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-03'	,	'asd6@mail.com'	,	'asd26@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Alex Wilton'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7898-1177'	,	'(88)95654-1151'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	8	, 'c9f0f895fb98ab9159f51fd0297e236d', 'ATIVO'	, 0,	'Apelido8'		,	'(88)99998-1111'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-04'	,	'asd7@mail.com'	,	'asd27@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Amanda Miller'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-1881'	,	'(88)98654-1161'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	9	, '45c48cce2e2d7fbdea1afc51c7c6ad26', 'ATIVO'	, 0,	'Apelido9'		,	'(88)99999-1112'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-04'	,	'asd8@mail.com'	,	'asd28@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Junior Oliveira'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7838-1441'	,	'(88)98674-7711'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	10	, 'd3d9446802a44259755d38e6d163e820', 'ATIVO'	, 0,	'Apelido10'		,	'(88)99999-1113'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-05'	,	'asd9@mail.com'	,	'asd29@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Maria José Calvalcante'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7848-1114'	,	'(88)98854-1881'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	11	, '6512bd43d9caa6e02c990b0a82652dca', 'ATIVO'	, 0,	'Apelido11'		,	'(88)99999-1114'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-05'	,	'asd10@mail.com'	,	'asd20@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Francisco Alves'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7838-1131'	,	'(88)98694-1991'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	12	, 'c20ad4d76fe97759aa27a0c99bff6710', 'ATIVO'	, 0,	'Apelido12'		,	'(88)99999-1115'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-06'	,	'asd11@mail.com'	,	'asd211@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Sandra Maria'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-1123'	,	'(88)98054-1101'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	13	, 'c51ce410c124a10e0db5e4b97fc2af39', 'ATIVO'	, 0,	'Apelido13'		,	'(88)99999-1116'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-06'	,	'asd12@mail.com'	,	'asd212@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Ian Lucas'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7838-1111'	,	'(88)92154-1211'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	14	, 'aab3238922bcc25a6f606eb525ffdc56', 'ATIVO'	, 0,	'Apelido14'		,	'(88)99999-1117'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-07'	,	'asd13@mail.com'	,	'asd213@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'Héllen Súzany'	,	'Observação'	,	'Profissão'	,	'F'	,	'(88)7898-1123'	,	'(88)32654-1321'	);
INSERT INTO pessoa (id, id_md5, status, versao, apelido, celular, data_cadastro, data_batismo, data_conversao, data_nascimento, email, email2, bairro, cep, cidade, estado, logradouro, pais, referencia, escolaridade, estado_civil, nome, observacao, profissao, sexo, telefone, telefone_emergencial)  VALUES (	15	, '9bf31c7ff062936a96d3c8bd1f8f2ff3', 'ATIVO'	, 0,	'Apelido15'		,	'(88)99999-1118'	, '2006-12-05'	,'2006-12-05'	,	'2006-12-05'	,	'1992-01-07'	,	'asd14@mail.com'	,	'asd214@mail.com'	,	'bairro'	,	'62.940-000'	,	'Morada Nova'	,	'CE'	,	'Rua tal, 213'	,	'Brasil'	,	'Referência'	,	'MEDIO_COMPLETO'	,	'SOLTEIRO'	,	'David Emanuell'	,	'Observação'	,	'Profissão'	,	'M'	,	'(88)7898-1131'	,	'(88)98434-1431'	); 

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

