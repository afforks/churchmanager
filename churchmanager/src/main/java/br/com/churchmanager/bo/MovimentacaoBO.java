package br.com.churchmanager.bo;

import br.com.churchmanager.dao.MovimentacaoDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.filter.MovimentacaoFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class MovimentacaoBO implements Serializable, Buscador<Movimentacao> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private MovimentacaoDAO dao;

	public void salvar(Movimentacao movimentacao) {
		this.validar(movimentacao);
		this.dao.salvar(movimentacao);
	}

	public Movimentacao atualizar(Movimentacao movimentacao) {
		this.validar(movimentacao);
		return (Movimentacao) this.dao.atualizar(movimentacao);
	}

	public void deletar(Movimentacao movimentacao) {
		this.dao.excluir(movimentacao);
	}

	public List<Movimentacao> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Movimentacao movimentacao) {
	}

	public Movimentacao buscarPorId(Serializable id) {
		return (Movimentacao) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Movimentacao> filtrar(MovimentacaoFilter movimentacaoFilter) {
		return this.dao.filtrar(this.dao, movimentacaoFilter);
	}
}