package br.com.churchmanager.bo;

import br.com.churchmanager.dao.UsuarioDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.model.filter.UsuarioFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class UsuarioBO implements Serializable, Buscador<Usuario> {
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioDAO dao;

	public void salvar(Usuario usuario) {
		this.validar(usuario);
		this.dao.salvar(usuario);
	}

	public void atualizar(Usuario usuario) {
		this.validar(usuario);
		this.dao.atualizar(usuario);
	}

	public void deletar(Usuario usuario) {
		this.dao.excluir(usuario);
	}

	public List<Usuario> listar() {
		return this.dao.listar(true, new String[0]);
	}

	public void validar(Usuario usuario) {
	}

	public Usuario buscarPorId(Serializable id) {
		return (Usuario) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Usuario> filtrar(UsuarioFilter usuarioFilter) {
		return this.dao.filtrar(this.dao, usuarioFilter);
	}

	public Usuario porEmail(String email) {
		return this.dao.porEmail(email);
	}
}