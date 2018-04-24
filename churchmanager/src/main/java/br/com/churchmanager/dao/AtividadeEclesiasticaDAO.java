package br.com.churchmanager.dao;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.AtividadeEclesiastica;

import java.io.Serializable;

public class AtividadeEclesiasticaDAO extends DAO<AtividadeEclesiastica> implements Serializable {
	private static final long serialVersionUID = 1L;

	public AtividadeEclesiasticaDAO() {
		super(AtividadeEclesiastica.class);
	}
}