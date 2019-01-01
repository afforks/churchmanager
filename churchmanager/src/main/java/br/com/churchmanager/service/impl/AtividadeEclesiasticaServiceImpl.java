package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.repository.AtividadeEclesiasticaRepository;
import br.com.churchmanager.service.AtividadeEclesiasticaService;

public class AtividadeEclesiasticaServiceImpl implements AtividadeEclesiasticaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private AtividadeEclesiasticaRepository repository;

	public AtividadeEclesiastica save(AtividadeEclesiastica atividade) {
		this.validar(atividade);
		return this.repository.save(atividade);
	}

	public AtividadeEclesiastica update(AtividadeEclesiastica atividade) {
		this.validar(atividade);
		return this.repository.save(atividade);
	}

	public void delete(AtividadeEclesiastica atividade) {
		this.repository.remove(atividade);
	}

	public List<AtividadeEclesiastica> findAll() {
		return this.repository.findAll();
	}

	public void validar(AtividadeEclesiastica atividade) {
		// TODO regras de negócio
	}

	public AtividadeEclesiastica findBy(Long id) {
		return this.repository.findBy(id);
	}

	@Override
	public LazyDataModel<AtividadeEclesiastica> lazyList(Filter filter) {
		return null;
	}

	@Override
	public EntityRepository<AtividadeEclesiastica, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(AtividadeEclesiastica t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public List<AtividadeEclesiastica> autoComplete(String value) {
		return null;
	}
}