package br.com.churchmanager.bo;

import br.com.churchmanager.dao.CategoriaMovimentacaoDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.CategoriaMovimentacao;
import br.com.churchmanager.model.filter.CategoriaMovimentacaoFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class CategoriaMovimentacaoBO implements Serializable, Buscador<CategoriaMovimentacao> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	private CategoriaMovimentacaoDAO dao;

	public void salvar(CategoriaMovimentacao categoria) {
		this.validar(categoria);
		this.dao.salvar(categoria);
	}

	public void atualizar(CategoriaMovimentacao categoria) {
		this.validar(categoria);
		this.dao.atualizar(categoria);
	}

	public void deletar(CategoriaMovimentacao categoria) {
		this.dao.excluir(categoria);
	}

	public List<CategoriaMovimentacao> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(CategoriaMovimentacao categoria) {
	}

	public CategoriaMovimentacao buscarPorId(Serializable id) {
		return (CategoriaMovimentacao) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<CategoriaMovimentacao> filtrar(CategoriaMovimentacaoFilter categoriaFilter) {
		return this.dao.filtrar(this.dao, categoriaFilter);
	}

	public List<CategoriaMovimentacao> autoCompletar(String value) {
		return this.dao.listarAutocompletar("nome", true, "nome", value);
	}
}