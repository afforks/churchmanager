package br.com.churchmanager.dao;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Movimentacao;

import java.io.Serializable;

public class MovimentacaoDAO extends DAO<Movimentacao> implements Serializable {
	private static final long serialVersionUID = 1823847283499L;

	public MovimentacaoDAO() {
		super(Movimentacao.class);
	}
}