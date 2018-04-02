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
		return this.dao.listar(true, new String[0]);
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