package br.com.churchmanager.dao;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.CategoriaMovimentacao;

import java.io.Serializable;

import javax.persistence.EntityManager;

public class CategoriaMovimentacaoDAO extends DAO<CategoriaMovimentacao> implements Serializable {
	private static final long serialVersionUID = 1L;

	public CategoriaMovimentacaoDAO() {
		super(CategoriaMovimentacao.class);
	}

	public CategoriaMovimentacaoDAO(EntityManager entityManager) {
		super(CategoriaMovimentacao.class, entityManager);
	}
}