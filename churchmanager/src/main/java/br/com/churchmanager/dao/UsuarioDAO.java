package br.com.churchmanager.dao;

import br.com.churchmanager.generic.dao.DAO;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAO extends DAO<Usuario> implements Serializable {
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario porEmail(String email) {
		ArrayList restricoes = new ArrayList();
		restricoes.add(Restrictions.eq("status", Status.ATIVO));
		restricoes.add(Restrictions.eq("email", email));
		return (Usuario) this.buscarPorAtributosERestricoes(Usuario.class, restricoes, (Collection) null);
	}
}