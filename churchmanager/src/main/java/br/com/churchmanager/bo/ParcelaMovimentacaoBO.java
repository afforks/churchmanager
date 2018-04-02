package br.com.churchmanager.bo;

import br.com.churchmanager.dao.ParcelaMovimentacaoDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.ParcelaMovimentacao;
import br.com.churchmanager.model.filter.ParcelaMovimentacaoFilter;
import br.com.churchmanager.model.group.DetalheMovimentacao;
import br.com.churchmanager.model.group.MovimentacaoAnual;
import br.com.churchmanager.model.group.MovimentacaoCategoria;
import br.com.churchmanager.model.group.Totais;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class ParcelaMovimentacaoBO implements Serializable, Buscador<ParcelaMovimentacao> {
	private static final long serialVersionUID = 1L;
	@Inject
	private ParcelaMovimentacaoDAO dao;

	public void salvar(ParcelaMovimentacao parcela) {
		this.validar(parcela);
		this.dao.salvar(parcela);
	}

	public ParcelaMovimentacao atualizar(ParcelaMovimentacao parcela) {
		this.validar(parcela);
		return (ParcelaMovimentacao) this.dao.atualizar(parcela);
	}

	public void deletar(ParcelaMovimentacao parcela) {
		this.dao.excluir(parcela);
	}

	public List<ParcelaMovimentacao> listar() {
		return this.dao.listar(true, new String[0]);
	}

	public void validar(ParcelaMovimentacao parcela) {
	}

	public ParcelaMovimentacao buscarPorId(Serializable id) {
		return (ParcelaMovimentacao) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<ParcelaMovimentacao> filtrar(ParcelaMovimentacaoFilter parcelaFilter) {
		return this.dao.filtrar(this.dao, parcelaFilter);
	}

	public List<ParcelaMovimentacao> busarParcelas(Movimentacao movimentacao) {
		return this.dao.busarParcelas(movimentacao);
	}

	public List<DetalheMovimentacao> ultimosLancamentos(ParcelaMovimentacaoFilter parcelaFilter) {
		return this.dao.ultimosLancamentos(parcelaFilter);
	}

	public Totais movimentacoes(ParcelaMovimentacaoFilter filter) {
		return this.dao.movimentacoes(filter);
	}

	public List<MovimentacaoCategoria> custosPorCategoria(ParcelaMovimentacaoFilter filter) {
		return this.dao.custosPorCategoria(filter);
	}

	public List<MovimentacaoAnual> movimentacaoUltimos12Meses(ParcelaMovimentacaoFilter filter) {
		return this.dao.movimentacaoUltimos12Meses(filter);
	}
}