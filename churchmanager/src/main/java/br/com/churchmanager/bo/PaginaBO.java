package br.com.churchmanager.bo;

import br.com.churchmanager.dao.PaginaDAO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Pagina;
import br.com.churchmanager.model.filter.PaginaFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class PaginaBO implements Serializable, Buscador<Pagina> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private PaginaDAO dao;

	public void salvar(Pagina pagina) throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(pagina);
		this.dao.salvar(pagina);
	}

	public void atualizar(Pagina pagina) throws NegocioException, ViolacaoDeRestricaoException, DadosException {
		this.validar(pagina);
		this.dao.atualizar(pagina);
	}

	public void deletar(Pagina pagina) {
		this.dao.excluir(pagina);
	}

	public List<Pagina> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Pagina pagina) throws NegocioException {
	}

	public Pagina buscarPorId(Serializable id) {
		return (Pagina) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Pagina> filtrar(PaginaFilter paginaFilter) {
		return this.dao.filtrar(this.dao, paginaFilter);
	}
}