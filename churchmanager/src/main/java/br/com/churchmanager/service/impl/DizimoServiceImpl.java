package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Dizimo;
import br.com.churchmanager.model.dto.PercentualDizimista;
import br.com.churchmanager.model.filter.DizimoFilter;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.repository.DizimoRepository;
import br.com.churchmanager.service.DizimoService;

public class DizimoServiceImpl implements DizimoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private DizimoRepository repository;

	public Dizimo save(Dizimo dizimo) {
		this.validar(dizimo);
		return this.repository.save(dizimo);
	}

	public Dizimo update(Dizimo dizimo) {
		this.validar(dizimo);
		return this.repository.save(dizimo);
	}

	public void delete(Dizimo dizimo) {
		this.repository.remove(dizimo);
	}

	public List<Dizimo> findAll() {
		return this.repository.findAll();
	}

	public void validar(Dizimo dizimo) {
		//
	}

	public Dizimo findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Dizimo> filtrar(DizimoFilter dizimoFilter) {
		//return this.repository.lazyList(this.repository);
		return null;
	}

	public PercentualDizimista percentualDizimista(DizimoFilter filter) {
		return this.repository.percentualDizimista(filter);
	}

	@Override
	public EntityRepository<Dizimo, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Dizimo t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Dizimo> lazyList(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dizimo> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}