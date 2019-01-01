package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.repository.CargoRepository;
import br.com.churchmanager.service.CargoService;

public class CargoServiceImpl implements CargoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CargoRepository repository;

	public Cargo save(Cargo cargo) {
		this.validar(cargo);
		return this.repository.save(cargo);
	}

	public Cargo update(Cargo cargo) {
		this.validar(cargo);
		return this.repository.save(cargo);
	}

	public void delete(Cargo cargo) {
		this.repository.remove(cargo);
	}

	public List<Cargo> findAll() {
		return this.repository.findAll();
	}

	public void validar(Cargo cargo) {
		// TODO regras de negócio
	}

	public Cargo findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Cargo> lazyList(Filter filter) {
		//return this.repository.lazyList(this.repository);
		return null;
	}

	@Override
	public EntityRepository<Cargo, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Cargo t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public List<Cargo> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}