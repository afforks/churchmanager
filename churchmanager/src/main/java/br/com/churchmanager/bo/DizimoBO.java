package br.com.churchmanager.bo;

import br.com.churchmanager.dao.DizimoDAO;
import br.com.churchmanager.dao.generic.Buscador;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Dizimo;
import br.com.churchmanager.model.filter.DizimoFilter;
import br.com.churchmanager.model.group.PercentualDizimista;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class DizimoBO implements Serializable, Buscador<Dizimo> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private DizimoDAO dao;

	public void salvar(Dizimo dizimo) throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(dizimo);
		this.dao.salvar(dizimo);
	}

	public void atualizar(Dizimo dizimo) throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(dizimo);
		this.dao.atualizar(dizimo);
	}

	public void deletar(Dizimo dizimo) {
		this.dao.excluir(dizimo);
	}

	public List<Dizimo> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Dizimo dizimo) throws NegocioException {
	}

	public Dizimo buscarPorId(Serializable id) {
		return (Dizimo) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Dizimo> filtrar(DizimoFilter dizimoFilter) {
		return this.dao.filtrar(this.dao, dizimoFilter);
	}

	public PercentualDizimista percentualDizimista(DizimoFilter filter) {
		return this.dao.percentualDizimista(filter);
	}
}