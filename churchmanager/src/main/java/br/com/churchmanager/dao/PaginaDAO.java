package br.com.churchmanager.dao;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Pagina;

import java.io.Serializable;

import javax.persistence.EntityManager;

public class PaginaDAO extends DAO<Pagina> implements Serializable {
	private static final long serialVersionUID = 1L;

	public PaginaDAO() {
		super(Pagina.class);
	}

	public PaginaDAO(EntityManager entityManager) {
		super(Pagina.class, entityManager);
	}
}