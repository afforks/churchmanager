package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.model.filter.MovimentacaoFilter;
import br.com.churchmanager.repository.MovimentacaoRepository;
import br.com.churchmanager.service.MovimentacaoService;

public class MovimentacaoServiceImpl implements MovimentacaoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private MovimentacaoRepository repository;

	public Movimentacao save(Movimentacao movimentacao) {
		this.validar(movimentacao);
		return this.repository.save(movimentacao);
	}

	public Movimentacao update(Movimentacao movimentacao) {
		this.validar(movimentacao);
		return this.repository.save(movimentacao);
	}

	public void delete(Movimentacao movimentacao) {
		this.repository.remove(movimentacao);
	}

	public List<Movimentacao> findAll() {
		return this.repository.findAll();
	}

	public void validar(Movimentacao movimentacao) {
		//
	}

	public Movimentacao findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Movimentacao> filtrar(MovimentacaoFilter movimentacaoFilter) {
		//return this.repository.lazyList(this.repository, movimentacaoFilter);
		return null;
	}

	@Override
	public EntityRepository<Movimentacao, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Movimentacao t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Movimentacao> lazyList(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movimentacao> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}