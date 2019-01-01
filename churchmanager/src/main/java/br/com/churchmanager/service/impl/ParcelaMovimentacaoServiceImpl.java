package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.ParcelaMovimentacao;
import br.com.churchmanager.model.dto.DetalheMovimentacao;
import br.com.churchmanager.model.dto.MovimentacaoAnual;
import br.com.churchmanager.model.dto.MovimentacaoCategoria;
import br.com.churchmanager.model.dto.Totais;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.model.filter.ParcelaMovimentacaoFilter;
import br.com.churchmanager.repository.ParcelaMovimentacaoRepository;
import br.com.churchmanager.service.ParcelaMovimentacaoService;

public class ParcelaMovimentacaoServiceImpl implements ParcelaMovimentacaoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ParcelaMovimentacaoRepository repository;

	public ParcelaMovimentacao save(ParcelaMovimentacao parcela) {
		this.validar(parcela);
		return this.repository.save(parcela);
	}

	public ParcelaMovimentacao update(ParcelaMovimentacao parcela) {
		this.validar(parcela);
		return this.repository.save(parcela);
	}

	public void delete(ParcelaMovimentacao parcela) {
		this.repository.remove(parcela);
	}

	public List<ParcelaMovimentacao> findAll() {
		return this.repository.findAll();
	}

	public void validar(ParcelaMovimentacao parcela) {
		//
	}

	public ParcelaMovimentacao findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<ParcelaMovimentacao> filtrar(ParcelaMovimentacaoFilter parcelaFilter) {
		// return this.repository.lazyList(this.repository);
		return null;
	}

	public List<ParcelaMovimentacao> busarParcelas(Movimentacao movimentacao) {
		return this.repository.busarParcelas(movimentacao);
	}

	public List<DetalheMovimentacao> ultimosLancamentos(ParcelaMovimentacaoFilter parcelaFilter) {
		return this.repository.ultimosLancamentos(parcelaFilter);
	}

	public Totais movimentacoes(ParcelaMovimentacaoFilter filter) {
		return this.repository.movimentacoes(filter);
	}

	public List<MovimentacaoCategoria> custosPorCategoria(ParcelaMovimentacaoFilter filter) {
		return this.repository.custosPorCategoria(filter);
	}

	public List<MovimentacaoAnual> movimentacaoUltimos12Meses(ParcelaMovimentacaoFilter filter) {
		return this.repository.movimentacaoUltimos12Meses(filter);
	}

	@Override
	public EntityRepository<ParcelaMovimentacao, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(ParcelaMovimentacao t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<ParcelaMovimentacao> lazyList(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParcelaMovimentacao> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}