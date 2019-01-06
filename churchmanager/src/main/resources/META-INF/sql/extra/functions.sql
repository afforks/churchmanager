/*  */
delimiter $$
create function calcular_idade (data_nascimento date) returns int
	begin
		declare idade int default 0;
		declare dia_aniv int default day(data_nascimento);
		declare mes_aniv int default month(data_nascimento);
		declare ano_aniv int default year(data_nascimento);
    
		declare dia_atual int default day(now());
		declare mes_atual int default month(now());
		declare ano_atual int default year(now());
    
		set idade = ano_atual - ano_aniv - 1;
    
		if mes_atual >= mes_aniv then
			if dia_atual >= dia_aniv then
				set idade = idade + 1;
			end if;
		end if;
    
    return idade;
end $$
delimiter ;

/*  
conforme LEI Nº 10.741, Art. 1o, DE 01 DE OUTUBRO DE 2003 (Estatuto do idoso)
conforme LEI Nº 8.069, Art. 2º, DE 13 DE JULHO DE 1990 (Estatuto da Criança e do Adolescente)
*/
delimiter $$
create function faixa_etaria (data_nascimento date) 
returns varchar(20)
	begin
		declare idade int default calcular_idade(data_nascimento);
		declare CRIANCAS varchar(20) default '(0-12) Crianças';                 
		declare ADOLESCENTES varchar(20) default '(13-18) Adolescentes';         
		declare ADULTOS varchar(20) default '(19-59) Adultos';                   
		declare IDOSOS varchar(20) default '(60+) Idosos';                     
        
		if idade <= 12 then return CRIANCAS;
			elseif idade >= 13 && idade <= 18 then return ADOLESCENTES;
			elseif idade >= 19 && idade <= 59 then return ADULTOS;
			else return IDOSOS;
		end if;    
end

/*  */
delimiter $$
create function ordem_faixa_etaria(data_nascimento date) 
returns int
	begin
		declare idade int default calcular_idade(data_nascimento);
		if idade <= 12 then return 1;
			elseif idade >= 13 && idade <= 18 then return 2;
			elseif idade >= 19 && idade <= 59 then return 3;
			else return 4;
		end if;    
end;


/**
*	
*/
delimiter $$
create function contagem_por_periodo(data datetime)
returns varchar(4)
begin
declare qtd long default ((select count(id) 
	from pessoa where year(data_cadastro) = year(data)
		and data_cadastro < data)) +1;
        
    declare res varchar(4) default concat('', qtd);
    
			if length(res) = 4 then return res;
			elseif length(res) = 3 then return concat('0', res);
			elseif length(res) = 2 then return concat('00', res);
			elseif length(res) = 1 then return concat('000', res);
        end if;
 end;