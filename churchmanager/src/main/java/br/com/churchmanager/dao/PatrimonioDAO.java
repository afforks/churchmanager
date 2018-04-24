package br.com.churchmanager.dao;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Patrimonio;

import java.io.Serializable;

public class PatrimonioDAO extends DAO<Patrimonio> implements Serializable {
	private static final long serialVersionUID = 1L;

	public PatrimonioDAO() {
		super(Patrimonio.class);
	}
}