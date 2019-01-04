package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Evento;
import br.com.churchmanager.model.filter.EventoFilter;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.repository.EventoRepository;
import br.com.churchmanager.service.EventoService;

public class EventoServiceImpl implements EventoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EventoRepository repository;

	public Evento save(Evento evento) {
		this.validar(evento);
		return this.repository.save(evento);
	}

	public Evento update(Evento evento) {
		this.validar(evento);
		return this.repository.save(evento);
	}

	public void delete(Evento evento) {
		this.repository.remove(evento);
	}

	public List<Evento> findAll() {
		return this.repository.findAll();
	}

	public void validar(Evento evento) {
		//
	}

	public Evento findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Evento> filtrar() {
		//return this.repository.lazyList(this.repository);
		return null;
	}

	public List<Evento> eventosDoMes() {
		return this.repository.eventosDoMes();
	}

	@Override
	public EntityRepository<Evento, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Evento t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Evento> lazyList(Filter filter) {
		return new LazyDataModel<>(repository);
	}

	@Override
	public List<Evento> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> eventosDoMes(EventoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
}