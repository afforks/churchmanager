package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Patrimonio;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.model.filter.PatrimonioFilter;
import br.com.churchmanager.repository.PatrimonioRepository;
import br.com.churchmanager.service.PatrimonioService;

public class PatrimonioServiceImpl implements PatrimonioService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PatrimonioRepository repository;

	public Patrimonio save(Patrimonio patrimonio)  {
		this.validar(patrimonio);
		return this.repository.save(patrimonio);
	}

	public Patrimonio update(Patrimonio patrimonio)  {
		this.validar(patrimonio);
		return this.repository.save(patrimonio);
	}

	public void delete(Patrimonio patrimonio) {
		this.repository.remove(patrimonio);
	}

	public List<Patrimonio> findAll() {
		return this.repository.findAll();
	}

	public void validar(Patrimonio patrimonio)  {
		//
	}

	public Patrimonio findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Patrimonio> filtrar(PatrimonioFilter patrimonioFilter) {
		//return this.repository.lazyList(this.repository);
		return null;
	}

	@Override
	public EntityRepository<Patrimonio, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Patrimonio t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Patrimonio> lazyList(Filter filter) {
		return new LazyDataModel<>(repository);
	}

	@Override
	public List<Patrimonio> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}