package br.com.churchmanager.bo;

import br.com.churchmanager.dao.AtividadeEclesiasticaDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.filter.AtividadeEclesiasticaFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class AtividadeEclesiasticaBO implements Serializable, Buscador<AtividadeEclesiastica> {
	
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	
	@Inject
	private AtividadeEclesiasticaDAO dao;

	public void salvar(AtividadeEclesiastica pagina) {
		this.validar(pagina);
		this.dao.salvar(pagina);
	}

	public void atualizar(AtividadeEclesiastica pagina) {
		this.validar(pagina);
		this.dao.atualizar(pagina);
	}

	public void deletar(AtividadeEclesiastica pagina) {
		this.dao.excluir(pagina);
	}

	public List<AtividadeEclesiastica> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(AtividadeEclesiastica pagina) {
	}

	public AtividadeEclesiastica buscarPorId(Serializable id) {
		return (AtividadeEclesiastica) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<AtividadeEclesiastica> filtrar(AtividadeEclesiasticaFilter paginaFilter) {
		return this.dao.filtrar(this.dao, paginaFilter);
	}
}