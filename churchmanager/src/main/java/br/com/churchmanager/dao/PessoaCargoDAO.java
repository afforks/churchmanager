package br.com.churchmanager.dao;

import br.com.churchmanager.generic.dao.DAO;
import br.com.churchmanager.model.PessoaCargo;

import java.io.Serializable;

public class PessoaCargoDAO extends DAO<PessoaCargo> implements Serializable {
	private static final long serialVersionUID = 19837459837458L;

	public PessoaCargoDAO() {
		super(PessoaCargo.class);
	}
}