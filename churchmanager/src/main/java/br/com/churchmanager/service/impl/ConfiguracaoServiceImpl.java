package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Configuracao;
import br.com.churchmanager.model.filter.ConfiguracaoFilter;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.repository.ConfiguracaoRepository;
import br.com.churchmanager.service.ConfiguaracaoService;

public class ConfiguracaoServiceImpl implements ConfiguaracaoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ConfiguracaoRepository repository;

	public Configuracao save(Configuracao configuracao) {
		this.validar(configuracao);
		return this.repository.save(configuracao);
	}

	public Configuracao update(Configuracao configuracao) {
		this.validar(configuracao);
		return this.repository.save(configuracao);
	}

	public void delete(Configuracao configuracao) {
		this.repository.remove(configuracao);
	}

	public List<Configuracao> findAll() {
		return this.repository.findAll();
	}

	public void validar(Configuracao configuracao) {
		//
	}

	public Configuracao findBy(Long id) {
		return this.repository.findBy(id);
	}

	public LazyDataModel<Configuracao> filtrar(ConfiguracaoFilter configuracaoFilter) {
		// return this.repository.lazyList(this.repository);
		return null;
	}

	@Deprecated
	public Configuracao buscarUltimaConfiguracao() {
		return null;
	}

	@Override
	public EntityRepository<Configuracao, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Configuracao t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Configuracao> lazyList(Filter filter) {
		return new LazyDataModel<>(repository);
	}

	@Override
	public List<Configuracao> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
