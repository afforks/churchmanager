package br.com.churchmanager.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.churchmanager.dao.PatrimonioDAO;
import br.com.churchmanager.dao.generic.Buscador;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.model.Patrimonio;
import br.com.churchmanager.model.filter.PatrimonioFilter;
import br.com.churchmanager.util.MyLazyDataModel;

public class PatrimonioBO implements Serializable, Buscador<Patrimonio> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private PatrimonioDAO dao;

	public void salvar(Patrimonio patrimonio) throws NegocioException {
		this.validar(patrimonio);
		this.dao.salvar(patrimonio);
	}

	public void atualizar(Patrimonio patrimonio) throws NegocioException {
		this.validar(patrimonio);
		this.dao.atualizar(patrimonio);
	}

	public void deletar(Patrimonio patrimonio) {
		this.dao.excluir(patrimonio);
	}

	public List<Patrimonio> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Patrimonio patrimonio) throws NegocioException {
		//
	}

	public Patrimonio buscarPorId(Long id) {
		return this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Patrimonio> filtrar(PatrimonioFilter patrimonioFilter) {
		return this.dao.filtrar(this.dao, patrimonioFilter);
	}
}