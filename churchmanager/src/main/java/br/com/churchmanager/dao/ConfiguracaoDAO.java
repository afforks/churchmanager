package br.com.churchmanager.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Configuracao;

public class ConfiguracaoDAO extends DAO<Configuracao> implements Serializable {

	private static final long serialVersionUID = 5923978036963L;

	public ConfiguracaoDAO() {
		super(Configuracao.class);
	}

	public ConfiguracaoDAO(Class<Configuracao> clazz, EntityManager entityManager) {
		super(clazz, entityManager);
	}

	public synchronized Configuracao buscarUltimaConfiguracao() {
		List<Criterion> restricoes = new ArrayList<>();
		Criteria criteria = criteria(restricoes, null, null);
		criteria.setProjection(Projections.max("id"));
		Long id = (Long) criteria.uniqueResult();
		Configuracao configuracao = new Configuracao();
		if (id != null) {
			configuracao = buscarPorId(id);
		}
		return configuracao;
	}
}
