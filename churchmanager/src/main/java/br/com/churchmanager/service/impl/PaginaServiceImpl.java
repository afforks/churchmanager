package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Pagina;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.model.filter.PaginaFilter;
import br.com.churchmanager.repository.PaginaRepository;
import br.com.churchmanager.service.PaginaService;

public class PaginaServiceImpl implements PaginaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PaginaRepository repository;

	public Pagina save(Pagina pagina) {
		this.validar(pagina);
		return this.repository.save(pagina);
	}

	public Pagina update(Pagina pagina) {
		this.validar(pagina);
		return this.repository.save(pagina);
	}

	public void delete(Pagina pagina) {
		this.repository.remove(pagina);
	}

	public List<Pagina> findAll() {
		return this.repository.findAll();
	}

	public void validar(Pagina pagina) {
		//
	}

	public Pagina findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Pagina> filtrar(PaginaFilter paginaFilter) {
		//return this.repository.lazyList(this.repository);
		return null;
	}

	@Override
	public EntityRepository<Pagina, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Pagina t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Pagina> lazyList(Filter filter) {
		return new LazyDataModel<>(repository);
	}

	@Override
	public List<Pagina> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}