package br.com.churchmanager.dao;

import br.com.churchmanager.generic.dao.DAO;
import br.com.churchmanager.model.Pagina;

import java.io.Serializable;

public class PaginaDAO extends DAO<Pagina> implements Serializable {
	private static final long serialVersionUID = 1L;

	public PaginaDAO() {
		super(Pagina.class);
	}
}