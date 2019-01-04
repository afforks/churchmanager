package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.CategoriaMovimentacao;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.repository.CategoriaMovimentacaoRepository;
import br.com.churchmanager.service.CategoriaMovimentacaoService;

public class CategoriaMovimentacaoServiceImpl implements CategoriaMovimentacaoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaMovimentacaoRepository repository;

	@Override
	public CategoriaMovimentacao save(CategoriaMovimentacao categoria) {
		this.validar(categoria);
		return this.repository.save(categoria);
	}

	@Override
	public CategoriaMovimentacao update(CategoriaMovimentacao categoria) {
		this.validar(categoria);
		return this.repository.save(categoria);
	}

	@Override
	public void delete(CategoriaMovimentacao categoria) {
		this.repository.remove(categoria);
	}

	@Override
	public List<CategoriaMovimentacao> findAll() {
		return this.repository.findAll();
	}

	public void validar(CategoriaMovimentacao categoria) {
		//
	}

	@Override
	public CategoriaMovimentacao findBy(Long id) {
		return this.repository.findBy(id);
	}

	@Override
	public EntityRepository<CategoriaMovimentacao, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(CategoriaMovimentacao t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<CategoriaMovimentacao> lazyList(Filter filter) {
		return new LazyDataModel<>(repository);
	}

	@Override
	public List<CategoriaMovimentacao> autoComplete(String value) {
		return repository.autoComplete(value);
	}

}