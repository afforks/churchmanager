package br.com.churchmanager.dao;

import br.com.churchmanager.generic.dao.DAO;
import br.com.churchmanager.model.Diretoria;

import java.io.Serializable;

public class DiretoriaDAO extends DAO<Diretoria> implements Serializable {
	private static final long serialVersionUID = 17654324321L;

	public DiretoriaDAO() {
		super(Diretoria.class);
	}
}