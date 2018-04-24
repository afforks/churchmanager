package br.com.churchmanager.dao;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Perfil;

import java.io.Serializable;

public class PerfilDAO extends DAO<Perfil> implements Serializable {
	private static final long serialVersionUID = 1L;

	public PerfilDAO() {
		super(Perfil.class);
	}
}