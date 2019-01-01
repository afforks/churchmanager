package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Tipo;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.model.filter.TipoFilter;
import br.com.churchmanager.repository.TipoRepository;
import br.com.churchmanager.service.TipoService;

public class TipoServiceImpl implements TipoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TipoRepository repository;

	public Tipo save(Tipo tipo) {
		this.validar(tipo);
		return this.repository.save(tipo);
	}

	public Tipo update(Tipo tipo) {
		this.validar(tipo);
		return this.repository.save(tipo);
	}

	public void delete(Tipo tipo) {
		this.repository.remove(tipo);
	}

	public List<Tipo> findAll() {
		return this.repository.findAll();
	}

	public void validar(Tipo tipo) {
		//
	}

	public Tipo findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Tipo> filtrar(TipoFilter tipoFilter) {
		// return this.repository.lazyList(this.repository);
		return null;
	}

	@Override
	public EntityRepository<Tipo, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Tipo t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Tipo> lazyList(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tipo> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}