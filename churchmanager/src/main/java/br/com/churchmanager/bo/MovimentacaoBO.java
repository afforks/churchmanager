package br.com.churchmanager.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.churchmanager.dao.MovimentacaoDAO;
import br.com.churchmanager.dao.generic.Buscador;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.filter.MovimentacaoFilter;
import br.com.churchmanager.util.MyLazyDataModel;

public class MovimentacaoBO implements Serializable, Buscador<Movimentacao> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private MovimentacaoDAO dao;

	public void salvar(Movimentacao movimentacao) throws NegocioException {
		this.validar(movimentacao);
		this.dao.salvar(movimentacao);
	}

	public Movimentacao atualizar(Movimentacao movimentacao) throws NegocioException {
		this.validar(movimentacao);
		return this.dao.atualizar(movimentacao);
	}

	public void deletar(Movimentacao movimentacao) {
		this.dao.excluir(movimentacao);
	}

	public List<Movimentacao> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Movimentacao movimentacao) throws NegocioException {
		//
	}

	public Movimentacao buscarPorId(Long id) {
		return this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Movimentacao> filtrar(MovimentacaoFilter movimentacaoFilter) {
		return this.dao.filtrar(this.dao, movimentacaoFilter);
	}
}