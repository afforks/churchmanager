/*  */
create view faixa_etaria as 
select 
p.data_cadastro dc, 
year(p.data_cadastro) as ano,
month(p.data_cadastro) as mes,
sum(case when p.sexo = 'M' then 1 else 0 end) as 'M', 
sum(case when p.sexo = 'F' then 1 else 0 end) as 'F', 
faixa_etaria(p.data_nascimento) as 'faixa_etaria'
	from pessoa as p
		group by faixa_etaria, ano, mes
        order by ordem_faixa_etaria(p.data_nascimento);
    





    
/*  */
create view tipo_status_total_mensal as
select m.tipo_movimentacao as tipo, 
pm.status_movimentacao as status,
date_format(pm.data_vencimento, '%m') as mes,
date_format(pm.data_vencimento, '%y') as ano,
pm.data_vencimento as data,
 sum(pm.valor_parcela) as valor 
	from movimentacao m
		inner join categoria_movimentacao c 
			on c.id = m.categoria_id
        inner join parcela_movimentacao pm 
			on pm.movimentacao_id = m.id
	group by m.tipo_movimentacao, pm.status_movimentacao, 
    month(pm.data_vencimento), year(pm.data_vencimento)
    order by pm.data_vencimento;
      



      
      
/*
create view custo_por_categoria as
select c.nome as nome, sum(pm.valor_parcela) as valor				
 from movimentacao m                             
		inner join categoria_movimentacao c      
			on c.id = m.categoria_id                
		 inner join parcela_movimentacao pm        
			on pm.movimentacao_id = m.id            
		where pm.status_movimentacao = 'PAGO'       
		 AND m.tipo_movimentacao='SAIDA'           
		 AND month(pm.data_pagamento) = @mes         
		 AND year(pm.data_pagamento) = @ano    
	group by c.nome;
	*/






    
/*  */
create view entradas_e_saidas_por_categoria as
select c.nome as nome, 
sum(pm.valor_parcela) as valor,
month(pm.data_pagamento) mes, 
year(pm.data_pagamento) ano, 
m.tipo_movimentacao as tipo

 from movimentacao m                             
		inner join categoria_movimentacao c      
			on c.id = m.categoria_id                
		 inner join parcela_movimentacao pm        
			on pm.movimentacao_id = m.id            
		
        where pm.data_pagamento is not null
        and pm.status_movimentacao = 'PAGO'
		     
	group by c.nome, mes, ano, tipo
    order by pm.data_vencimento DESC;






    
/*  */
create view pessoas_por_atividade_eclesiastica as
select max(p.data_cadastro) as data,
ae.nome as categoria, count(ae.id) as quantidade
from pessoa p
	inner join pessoa_atividade_eclesiastica pae
		on pae.pessoa_id = p.id
	inner join atividade_eclesiastica ae
		on ae.id = pae.atividade_eclesiastica_id
	group by year(p.data_cadastro), month(p.data_cadastro), categoria;







/*  */
create view aniversariantes as 
-- alter view aniversariantes as
select 
	month(data_cadastro) as mes_cadastro, 
	year(data_cadastro) as ano_cadastro,
	nome, apelido, data_nascimento,
	calcular_idade(data_nascimento) as idade from pessoa 
order by day(data_nascimento)









    
/* */ 
create view total_membros_periodo as
select 
    year(data_cadastro) as ano,
    month(data_cadastro) as mes,
    data_cadastro as dc,
    count(id) as total, 
    sum(case when sexo = 'M' then 1 else 0 end) as 'M', 
	sum(case when sexo = 'F' then 1 else 0 end) as 'F'
from pessoa
	group by ano, mes;






/*  */
create view totalizadores as
select 
month(pm.data_vencimento) as mes,
year(pm.data_vencimento) as ano,
	sum(case when m.tipo_movimentacao = 'ENTRADA' 
		and pm.status_movimentacao = 'PAGO' 
			then pm.valor_parcela else 0 end) as 'recebidas',
	sum(case when m.tipo_movimentacao = 'ENTRADA' 
		and pm.status_movimentacao = 'EM_ABERTO' 
			then pm.valor_parcela else 0 end) as 'a_receber',
	sum(case when m.tipo_movimentacao = 'SAIDA' 
		and pm.status_movimentacao = 'PAGO' 
			then pm.valor_parcela else 0 end) as 'pagas',
	sum(case when m.tipo_movimentacao = 'SAIDA' 
		and pm.status_movimentacao = 'EM_ABERTO' 
			then pm.valor_parcela else 0 end) as 'a_pagar'
from parcela_movimentacao pm
	inner join movimentacao m
		on m.id = pm.movimentacao_id
	group by mes, ano
    order by pm.data_vencimento;
    
    
    
    
    
/*  */
create view movimentacoes as 
select month(pm.data_vencimento) as mes, year(pm.data_vencimento) as ano,
pm.nome as nome, pm.descricao as descricao, pm.data_vencimento as data_vencimento, 
pm.data_pagamento as data_pagamento, pm.valor_parcela as valor, 
m.tipo_movimentacao as tipo, pm.status_movimentacao as status,
m.forma_movimentacao forma
	from parcela_movimentacao pm
		inner join movimentacao m	
			on pm.movimentacao_id = m.id;
            


/*	*/
create view relatorio_dizimos as
select p.id as 'PID', matricula as 'ID', p.nome as 'Nome', year(d.data_recebimento) as 'Ano',
max(case when month(d.data_recebimento) = 1 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd1',
max(case when month(d.data_recebimento) = 2 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd2',
max(case when month(d.data_recebimento) = 3 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd3',
max(case when month(d.data_recebimento) = 4 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd4',
max(case when month(d.data_recebimento) = 5 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd5',
max(case when month(d.data_recebimento) = 6 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd6',
max(case when month(d.data_recebimento) = 7 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd7',
max(case when month(d.data_recebimento) = 8 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd8',
max(case when month(d.data_recebimento) = 9 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd9',
max(case when month(d.data_recebimento) = 10 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd10',
max(case when month(d.data_recebimento) = 11 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd11',
max(case when month(d.data_recebimento) = 12 and d.is_13 = 0 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd12',
max(case when d.is_13 = 1 then date_format(d.data_recebimento, '%d/%m') else '' end) as 'd13',

sum(case when month(d.data_recebimento) = 1 and d.is_13 = 0 then d.valor_dizimo else '' end) as '1',
sum(case when month(d.data_recebimento) = 2 and d.is_13 = 0 then d.valor_dizimo else '' end) as '2',
sum(case when month(d.data_recebimento) = 3 and d.is_13 = 0 then d.valor_dizimo else '' end) as '3',
sum(case when month(d.data_recebimento) = 4 and d.is_13 = 0 then d.valor_dizimo else '' end) as '4',
sum(case when month(d.data_recebimento) = 5 and d.is_13 = 0 then d.valor_dizimo else '' end) as '5',
sum(case when month(d.data_recebimento) = 6 and d.is_13 = 0 then d.valor_dizimo else '' end) as '6',
sum(case when month(d.data_recebimento) = 7 and d.is_13 = 0 then d.valor_dizimo else '' end) as '7',
sum(case when month(d.data_recebimento) = 8 and d.is_13 = 0 then d.valor_dizimo else '' end) as '8',
sum(case when month(d.data_recebimento) = 9 and d.is_13 = 0 then d.valor_dizimo else '' end) as '9',
sum(case when month(d.data_recebimento) = 10 and d.is_13 = 0 then d.valor_dizimo else '' end) as '10',
sum(case when month(d.data_recebimento) = 11 and d.is_13 = 0 then d.valor_dizimo else '' end) as '11',
sum(case when month(d.data_recebimento) = 12 and d.is_13 = 0 and d.is_13 = 0 then d.valor_dizimo else '' end) as '12',
sum(case when d.is_13 = 1 then d.valor_dizimo else '' end) as '13',

sum(case when month(d.data_recebimento) = 1 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o1',
sum(case when month(d.data_recebimento) = 2 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o2',
sum(case when month(d.data_recebimento) = 3 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o3',
sum(case when month(d.data_recebimento) = 4 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o4',
sum(case when month(d.data_recebimento) = 5 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o5',
sum(case when month(d.data_recebimento) = 6 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o6',
sum(case when month(d.data_recebimento) = 7 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o7',
sum(case when month(d.data_recebimento) = 8 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o8',
sum(case when month(d.data_recebimento) = 9 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o9',
sum(case when month(d.data_recebimento) = 10 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o10',
sum(case when month(d.data_recebimento) = 11 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o11',
sum(case when month(d.data_recebimento) = 12 and d.is_13 = 0 then d.valor_oferta else '' end) as 'o12',
sum(case when d.is_13 = 1 then d.valor_oferta else '' end) as 'o13'

from dizimo d
	inner join pessoa p
		on p.id = d.pessoa_id

group by d.pessoa_id, year(d.data_recebimento);
 
 
 
 
/*	*/
create view percentual_dizimistas as
select month(d.data_referencia) as 'MES' , 
		year(d.data_referencia) as 'ANO', 
		count(distinct d.pessoa_id) * 100 / (select count(p.id) 
			from pessoa p 
			where p.status = 'ATIVO' and 
			p.data_cadastro <= d.data_referencia) as 'PORCENTAGEM' 
	from dizimo d
group by year(d.data_referencia), month(d.data_referencia);



create view dizimistas as
select
        distinct p.nome as nome,
        d.data_referencia as data
    from
        dizimo d 
    inner join
        pessoa p 
            on d.pessoa_id=p.id 
    order by p.nome;