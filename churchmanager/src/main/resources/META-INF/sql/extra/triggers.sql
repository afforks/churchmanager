/*	NÃO UTILIZADO NO PROJETO	*/

delimiter $
create trigger atualizar_id_md5_pessoa after insert on pessoa
  for each row begin
	update pessoa set id_md5 = md5(id) where id = new.id;
end$



delimiter $
create trigger atualizar_matricula_pessoa AFTER insert on pessoa
  for each row begin
	update pessoa set matricula = concat(year(new.data_cadastro), 
		case when month(new.data_cadastro) < 7 then '01' else '02' end , 
			contagem_por_periodo(new.data_cadastro)) 
    where id = new.id;
end$ 