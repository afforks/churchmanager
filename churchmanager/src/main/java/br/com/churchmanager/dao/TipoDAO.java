package br.com.churchmanager.dao;

import br.com.churchmanager.generic.dao.DAO;
import br.com.churchmanager.model.Tipo;

import java.io.Serializable;

public class TipoDAO extends DAO<Tipo> implements Serializable {
	private static final long serialVersionUID = 128374898798798L;

	public TipoDAO() {
		super(Tipo.class);
	}
}