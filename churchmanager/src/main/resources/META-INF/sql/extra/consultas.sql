SELECT distinct categoria, sum(quantidade) as quantidade FROM pessoas_por_atividade_eclesiastica 
where data < '2016-12-30'
group by categoria;

