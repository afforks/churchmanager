package br.com.churchmanager.dao;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> implements Serializable {
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario porEmail(String email) {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		restricoes.add(Restrictions.eq("status", Status.ATIVO));
		restricoes.add(Restrictions.eq("email", email));
		return this.buscarPorAtributosERestricoes(Usuario.class, restricoes, null);
	}
}