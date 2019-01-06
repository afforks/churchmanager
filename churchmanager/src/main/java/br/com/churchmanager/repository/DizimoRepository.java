package br.com.churchmanager.repository;

import java.math.BigDecimal;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.churchmanager.model.Dizimo;
import br.com.churchmanager.model.dto.PercentualDizimista;
import br.com.churchmanager.model.filter.DizimoFilter;

@Repository
public abstract class DizimoRepository implements EntityRepository<Dizimo, Long> {

	@Inject
	private EntityManager entityManager;

	public PercentualDizimista percentualDizimista(DizimoFilter filter) {
		String sql = "select * from percentual_dizimistas where mes = :mes and ano = :ano";
		Session session = this.entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		int mes = Integer.parseInt(filter.getMes());
		int ano = Integer.parseInt(filter.getAno());
		query.setParameter("mes", Integer.valueOf(mes));
		query.setParameter("ano", Integer.valueOf(ano));
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		Map<?, ?> dados = (Map<?, ?>) query.uniqueResult();
		PercentualDizimista percentual = new PercentualDizimista(mes, ano, BigDecimal.valueOf(0));
		if (dados != null) {
			percentual = new PercentualDizimista(((Integer) dados.get("MES")).intValue(),
					((Integer) dados.get("ANO")).intValue(), (BigDecimal) dados.get("PORCENTAGEM"));
		}
		return percentual;
	}

}