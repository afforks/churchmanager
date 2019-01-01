package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Diretoria;
import br.com.churchmanager.model.filter.DiretoriaFilter;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.repository.DiretoriaRepository;
import br.com.churchmanager.service.DiretoriaService;

public class DiretoriaServiceImpl implements DiretoriaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private DiretoriaRepository repository;

	public Diretoria save(Diretoria evento) {
		this.validar(evento);
		return this.repository.save(evento);
	}

	public Diretoria update(Diretoria evento) {
		this.validar(evento);
		return this.repository.save(evento);
	}

	public void delete(Diretoria evento) {
		this.repository.remove(evento);
	}

	public List<Diretoria> findAll() {
		return this.repository.findAll();
	}

	public void validar(Diretoria evento) {
		if (evento.getPessoaCargos() == null || evento.getPessoaCargos().isEmpty()) {
			throw new NegocioException("É necessário selecionar os membros da diretoria");
		}
	}

	public Diretoria findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Diretoria> filtrar(DiretoriaFilter eventoFilter) {
		// return this.repository.lazyList(this.repository);
		return null;
	}

	@Override
	public EntityRepository<Diretoria, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Diretoria t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Diretoria> lazyList(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Diretoria> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}