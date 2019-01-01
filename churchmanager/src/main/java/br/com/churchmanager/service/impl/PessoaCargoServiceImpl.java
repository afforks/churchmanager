package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.PessoaCargo;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.model.filter.PessoaCargoFilter;
import br.com.churchmanager.repository.PessoaCargoRepository;
import br.com.churchmanager.service.PessoaCargoService;

public class PessoaCargoServiceImpl implements PessoaCargoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PessoaCargoRepository repository;

	public PessoaCargo save(PessoaCargo pc) {
		this.validar(pc);
		return this.repository.save(pc);
	}

	public PessoaCargo update(PessoaCargo pc) {
		this.validar(pc);
		return this.repository.save(pc);
	}

	public void delete(PessoaCargo pc) {
		this.repository.remove(pc);
	}

	public List<PessoaCargo> findAll() {
		return this.repository.findAll();
	}

	public void validar(PessoaCargo pc) {
		if (pc.getPessoa() == null) {
			throw new NegocioException("É necessário selecionar uma pessoa!");
		} else if (pc.getCargo() == null) {
			throw new NegocioException("É necessário selecionar um cargo!");
		}
	}

	public PessoaCargo findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<PessoaCargo> filtrar(PessoaCargoFilter pcFilter) {
		// return this.repository.lazyList(this.repository);
		return null;
	}

	@Override
	public EntityRepository<PessoaCargo, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(PessoaCargo t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<PessoaCargo> lazyList(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaCargo> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}