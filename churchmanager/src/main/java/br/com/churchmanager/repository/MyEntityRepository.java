package br.com.churchmanager.repository;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.filter.Filter;

public interface MyEntityRepository<T> extends EntityRepository<T, Long> {

	LazyDataModel<AtividadeEclesiastica> lazyList(Filter filter);

	List<AtividadeEclesiastica> autoComplete(String value);

}
