package br.com.churchmanager.jsf.primefaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.deltaspike.data.api.EntityRepository;
import org.primefaces.model.SortOrder;

@SuppressWarnings("unchecked")
public class LazyDataModel<T> extends org.primefaces.model.LazyDataModel<T> implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private EntityRepository<T, Long> entityRepository;

	public LazyDataModel(EntityRepository<T, Long> entityRepository) {
		this.entityRepository = entityRepository;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		setRowCount(entityRepository.countLike(null).intValue());
		return entityRepository.findBy(null, first, pageSize);
	}

}