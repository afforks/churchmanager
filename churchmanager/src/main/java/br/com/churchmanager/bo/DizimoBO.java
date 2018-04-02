package br.com.churchmanager.bo;

import br.com.churchmanager.dao.DizimoDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Dizimo;
import br.com.churchmanager.model.filter.DizimoFilter;
import br.com.churchmanager.model.group.PercentualDizimista;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class DizimoBO implements Serializable, Buscador<Dizimo> {
	private static final long serialVersionUID = 1L;
	@Inject
	private DizimoDAO dao;

	public void salvar(Dizimo dizimo) {
		this.validar(dizimo);
		this.dao.salvar(dizimo);
	}

	public void atualizar(Dizimo dizimo) {
		this.validar(dizimo);
		this.dao.atualizar(dizimo);
	}

	public void deletar(Dizimo dizimo) {
		this.dao.excluir(dizimo);
	}

	public List<Dizimo> listar() {
		return this.dao.listar(true, new String[0]);
	}

	public void validar(Dizimo dizimo) {
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