package br.com.churchmanager.service;

import java.io.Serializable;
import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.filter.Filter;

public interface Service<T> extends Serializable {

	T save(T t);

	T update(T t);

	List<T> findAll();

	T findBy(Long id);

	void delete(T t);

	EntityRepository<T, Long> getRepository();

	void updateStatus(T t);
	
	LazyDataModel<T> lazyList(Filter filter);
	
	List<T> autoComplete(String value);

}