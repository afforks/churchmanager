package br.com.churchmanager.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.churchmanager.dao.TipoDAO;
import br.com.churchmanager.dao.generic.Buscador;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.model.Tipo;
import br.com.churchmanager.model.filter.TipoFilter;
import br.com.churchmanager.util.MyLazyDataModel;

public class TipoBO implements Serializable, Buscador<Tipo> {
	private static final long serialVersionUID = 11236782348L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private TipoDAO dao;

	public void salvar(Tipo tipo) throws NegocioException {
		this.validar(tipo);
		this.dao.salvar(tipo);
	}

	public void atualizar(Tipo tipo) throws NegocioException {
		this.validar(tipo);
		this.dao.atualizar(tipo);
	}

	public void deletar(Tipo tipo) {
		this.dao.excluir(tipo);
	}

	public List<Tipo> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Tipo tipo) throws NegocioException {
		//
	}

	@Override
	public Tipo buscarPorId(Long id) {
		return this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Tipo> filtrar(TipoFilter tipoFilter) {
		return this.dao.filtrar(this.dao, tipoFilter);
	}
}