package br.com.churchmanager.dao;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.AtividadeEclesiastica;

import java.io.Serializable;

import javax.persistence.EntityManager;

public class AtividadeEclesiasticaDAO extends DAO<AtividadeEclesiastica> implements Serializable {
	private static final long serialVersionUID = 1L;

	public AtividadeEclesiasticaDAO() {
		super(AtividadeEclesiastica.class);
	}

	public AtividadeEclesiasticaDAO(EntityManager entityManager) {
		super(AtividadeEclesiastica.class, entityManager);
	}
}