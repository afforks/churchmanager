package br.com.churchmanager.repository;

import static br.com.churchmanager.util.DataUtil.ano;
import static br.com.churchmanager.util.DataUtil.semestre;
import static br.com.churchmanager.util.DataUtil.stringParaDate;
import static br.com.churchmanager.util.DataUtil.ultimoDiaDoMes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.dto.Aniversariante;
import br.com.churchmanager.model.dto.Dizimista;
import br.com.churchmanager.model.dto.MembrosPorFaixaEtaria;
import br.com.churchmanager.model.dto.PessoaAtividaEclesiastica;
import br.com.churchmanager.model.filter.PessoaFilter;

@Repository
public abstract class PessoaRepository implements EntityRepository<Pessoa, Long> {

	@Inject
	private EntityManager entityManager;

	public BigDecimal[] quantidadeDeMembros(PessoaFilter filter) {
		String sql = "select sum(total) as total, sum(M) as M, sum(F) as F from total_membros_periodo where dc <= :data";
		Session session = this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		String mes = filter.getMes();
		String ano = filter.getAno();
		int dia = ultimoDiaDoMes(stringParaDate("01" + "/" + mes + "/" + ano));

		query.setParameter("data", stringParaDate(dia + "/" + mes + "/" + ano));

		Map<?, ?> dados = (Map<?, ?>) query.uniqueResult();
		return new BigDecimal[] { (BigDecimal) dados.get("total"), (BigDecimal) dados.get("M"),
				(BigDecimal) dados.get("F") };
	}

	public List<Aniversariante> aniversariantesDoMes(PessoaFilter filter) {
		int mes = Integer.parseInt((filter.getMes()));
		int ano = Integer.parseInt((filter.getAno()));
		String sql = "select * from aniversariantes where ano_cadastro <= :ano_atual and month(data_nascimento) = :mes_atual ";
		ArrayList<Aniversariante> resultList = new ArrayList<>();
		Session session = this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("mes_atual", mes);
		query.setParameter("ano_atual", ano);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> data = query.list();
		Iterator<?> arg8 = data.iterator();

		while (arg8.hasNext()) {
			Object o = arg8.next();
			Map<?, ?> map = (Map<?, ?>) o;
			resultList.add(new Aniversariante((String) map.get("nome"), (String) map.get("apelido"),
					(Date) map.get("data_nascimento"), ((Integer) map.get("idade")).intValue()));
		}

		return resultList;
	}

	public List<MembrosPorFaixaEtaria> membresiaFaixaEtaria(PessoaFilter filter) {
		String sql = "select sum(M) as M, sum(F) as F, faixa_etaria from faixa_etaria where dc <= :data  group by faixa_etaria";
		ArrayList<MembrosPorFaixaEtaria> resultList = new ArrayList<>();
		Session session = this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		String mes = filter.getMes();
		String ano = filter.getAno();
		int dia = ultimoDiaDoMes(stringParaDate("01" + "/" + mes + "/" + ano));

		query.setParameter("data", stringParaDate(dia + "/" + mes + "/" + ano));

		List<?> data = query.list();
		Iterator<?> arg7 = data.iterator();

		while (arg7.hasNext()) {
			Object o = arg7.next();
			Map<?, ?> map = (Map<?, ?>) o;
			resultList.add(new MembrosPorFaixaEtaria((String) map.get("faixa_etaria"), (BigDecimal) map.get("M"),
					(BigDecimal) map.get("F")));
		}

		return resultList;
	}

	public List<PessoaAtividaEclesiastica> pessoasPorAtividadeEclesiastica(PessoaFilter filter) {
		String sql = "SELECT distinct categoria, sum(quantidade) as quantidade FROM pessoas_por_atividade_eclesiastica where data < :data group by categoria";
		ArrayList<PessoaAtividaEclesiastica> resultList = new ArrayList<>();
		Session session = this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		String mes = filter.getMes();
		String ano = filter.getAno();
		int dia = ultimoDiaDoMes(stringParaDate("01" + "/" + mes + "/" + ano));

		query.setParameter("data", stringParaDate(dia + "/" + mes + "/" + ano));

		List<?> data = query.list();
		Iterator<?> arg6 = data.iterator();

		while (arg6.hasNext()) {
			Object o = arg6.next();
			Map<?, ?> map = (Map<?, ?>) o;
			resultList.add(
					new PessoaAtividaEclesiastica((String) map.get("categoria"), (BigDecimal) map.get("quantidade")));
		}

		return resultList;
	}

	public String gerarMatricula(Date dataCadastro) {
		String sql = "select contagem_por_periodo(:data) as qtd_registros";
		Session session = this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		query.setParameter("data", dataCadastro);

		Map<?, ?> dados = (Map<?, ?>) query.uniqueResult();
		String qtdPorPeriodo = (String) dados.get("qtd_registros");
		return (ano() + semestre() + qtdPorPeriodo);
	}

	public List<Dizimista> listarDizimistas(PessoaFilter filter) {
		String sql = "select nome, data from dizimistas where MONTH(data) = :mes and YEAR(data) = :ano order by nome";
		ArrayList<Dizimista> dizimistas = new ArrayList<>();
		Session session = this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		String mes = filter.getMes();
		String ano = filter.getAno();

		query.setParameter("mes", mes);
		query.setParameter("ano", ano);

		List<?> data = query.list();
		Iterator<?> iterador = data.iterator();

		while (iterador.hasNext()) {
			Object o = iterador.next();
			Map<?, ?> map = (Map<?, ?>) o;
			dizimistas.add(new Dizimista((String) map.get("nome")));
		}

		return dizimistas;

	}

	public abstract Pessoa findByMatricula(String matricula);

}