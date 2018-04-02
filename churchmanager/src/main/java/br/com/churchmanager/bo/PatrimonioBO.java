package br.com.churchmanager.bo;

import br.com.churchmanager.dao.PatrimonioDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Patrimonio;
import br.com.churchmanager.model.filter.PatrimonioFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class PatrimonioBO implements Serializable, Buscador<Patrimonio> {
	private static final long serialVersionUID = 1L;
	@Inject
	private PatrimonioDAO dao;

	public void salvar(Patrimonio patrimonio) {
		this.validar(patrimonio);
		this.dao.salvar(patrimonio);
	}

	public void atualizar(Patrimonio patrimonio) {
		this.validar(patrimonio);
		this.dao.atualizar(patrimonio);
	}

	public void deletar(Patrimonio patrimonio) {
		this.dao.excluir(patrimonio);
	}

	public List<Patrimonio> listar() {
		return this.dao.listar(true, new String[0]);
	}

	public void validar(Patrimonio patrimonio) {
	}

	public Patrimonio buscarPorId(Serializable id) {
		return (Patrimonio) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Patrimonio> filtrar(PatrimonioFilter patrimonioFilter) {
		return this.dao.filtrar(this.dao, patrimonioFilter);
	}
}