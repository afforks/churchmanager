package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Perfil;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.model.filter.PerfilFilter;
import br.com.churchmanager.repository.PerfilRepository;
import br.com.churchmanager.service.PerfilService;

public class PerfilServiceImpl implements PerfilService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PerfilRepository repository;

	public Perfil save(Perfil perfil) {
		this.validar(perfil);
		return this.repository.save(perfil);
	}

	public Perfil update(Perfil perfil) {
		this.validar(perfil);
		return this.repository.save(perfil);
	}

	public void delete(Perfil perfil) {
		this.repository.remove(perfil);
	}

	public List<Perfil> findAll() {
		return this.repository.findAll();
	}

	public void validar(Perfil perfil) {
		//
	}

	public Perfil findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Perfil> filtrar(PerfilFilter perfilFilter) {
		//return this.repository.lazyList(this.repository);
		return null;
	}

	@Override
	public EntityRepository<Perfil, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Perfil t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Perfil> lazyList(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Perfil> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}