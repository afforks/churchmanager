package br.com.churchmanager.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.churchmanager.dao.PerfilDAO;
import br.com.churchmanager.dao.generic.Buscador;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.model.Perfil;
import br.com.churchmanager.model.filter.PerfilFilter;
import br.com.churchmanager.util.MyLazyDataModel;

public class PerfilBO implements Serializable, Buscador<Perfil> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private PerfilDAO dao;

	public void salvar(Perfil perfil) throws NegocioException {
		this.validar(perfil);
		this.dao.salvar(perfil);
	}

	public void atualizar(Perfil perfil) throws NegocioException {
		this.validar(perfil);
		this.dao.atualizar(perfil);
	}

	public void deletar(Perfil perfil) {
		this.dao.excluir(perfil);
	}

	public List<Perfil> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Perfil perfil) throws NegocioException {
		//
	}

	public Perfil buscarPorId(Long id) {
		return this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Perfil> filtrar(PerfilFilter perfilFilter) {
		return this.dao.filtrar(this.dao, perfilFilter);
	}
}