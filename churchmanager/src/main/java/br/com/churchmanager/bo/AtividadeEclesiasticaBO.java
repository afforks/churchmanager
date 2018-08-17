package br.com.churchmanager.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.churchmanager.dao.AtividadeEclesiasticaDAO;
import br.com.churchmanager.dao.generic.Buscador;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.filter.AtividadeEclesiasticaFilter;
import br.com.churchmanager.util.MyLazyDataModel;

public class AtividadeEclesiasticaBO implements Serializable, Buscador<AtividadeEclesiastica> {

	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private AtividadeEclesiasticaDAO dao;

	public void salvar(AtividadeEclesiastica atividade) throws NegocioException {
		this.validar(atividade);
		this.dao.salvar(atividade);
	}

	public void atualizar(AtividadeEclesiastica atividade) throws NegocioException {
		this.validar(atividade);
		this.dao.atualizar(atividade);
	}

	public void deletar(AtividadeEclesiastica atividade) {
		this.dao.excluir(atividade);
	}

	public List<AtividadeEclesiastica> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(AtividadeEclesiastica atividade) throws NegocioException {
		//
	}

	public AtividadeEclesiastica buscarPorId(Long id) {
		return this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<AtividadeEclesiastica> filtrar(AtividadeEclesiasticaFilter atividadeFilter) {
		return this.dao.filtrar(this.dao, atividadeFilter);
	}
}