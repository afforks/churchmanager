package br.com.churchmanager.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.churchmanager.dao.UsuarioDAO;
import br.com.churchmanager.dao.generic.Buscador;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.model.filter.UsuarioFilter;
import br.com.churchmanager.util.MyLazyDataModel;

public class UsuarioBO implements Serializable, Buscador<Usuario> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private UsuarioDAO dao;

	public void salvar(Usuario usuario) throws NegocioException {
		this.validar(usuario);
		this.dao.salvar(usuario);
	}

	public void atualizar(Usuario usuario) throws NegocioException {
		this.validar(usuario);
		this.dao.atualizar(usuario);
	}

	public void deletar(Usuario usuario) {
		this.dao.excluir(usuario);
	}

	public List<Usuario> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Usuario usuario) throws NegocioException {
		//
	}

	public Usuario buscarPorId(Long id) {
		return this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Usuario> filtrar(UsuarioFilter usuarioFilter) {
		return this.dao.filtrar(this.dao, usuarioFilter);
	}

	public Usuario porEmail(String email) {
		return this.dao.porEmail(email);
	}
}