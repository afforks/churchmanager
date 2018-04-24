package br.com.churchmanager.dao;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Dizimo;
import br.com.churchmanager.model.filter.DizimoFilter;
import br.com.churchmanager.model.group.PercentualDizimista;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class DizimoDAO extends DAO<Dizimo> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager entityManager;

	public DizimoDAO() {
		super(Dizimo.class);
	}

	public PercentualDizimista percentualDizimista(DizimoFilter filter) {
		String sql = "select * from percentual_dizimistas where mes = :mes and ano = :ano";
		Session session = (Session) this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		int mes = Integer.valueOf(filter.getMes()).intValue();
		int ano = Integer.valueOf(filter.getAno()).intValue();
		query.setParameter("mes", Integer.valueOf(mes));
		query.setParameter("ano", Integer.valueOf(ano));
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		Map<?, ?> dados = (Map<?, ?>) query.uniqueResult();
		PercentualDizimista percentual = new PercentualDizimista(mes, ano, BigDecimal.valueOf(0));
		if(dados != null) {
			percentual = new PercentualDizimista(((Integer) dados.get("MES")).intValue(),
				((Integer) dados.get("ANO")).intValue(), (BigDecimal) dados.get("PORCENTAGEM"));
		}
		return percentual;
	}
}