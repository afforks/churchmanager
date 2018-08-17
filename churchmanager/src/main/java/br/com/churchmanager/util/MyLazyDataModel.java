package br.com.churchmanager.util;

import br.com.churchmanager.dao.generic.Alias;
import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.EntidadeGenerica;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class MyLazyDataModel<T extends EntidadeGenerica> extends LazyDataModel<T> implements Serializable {
	
	private static final long serialVersionUID = 1705877968617746122L;
	
	private Class<T> clazz;
	private List<Criterion> restrictions;
	private List<Projection> projections;
	private List<Alias> aliases;
	private Boolean includeDistinctRootEntity;
	private DAO<T> dao;

	public MyLazyDataModel(DAO<T> dao, Class<T> clazz, List<Criterion> restrictions, List<Projection> projections,
			List<Alias> aliases, Boolean includeDistinctRootEntity) {
		this.dao = dao;
		this.clazz = clazz;
		this.restrictions = restrictions;
		this.projections = projections;
		this.aliases = aliases;
		this.includeDistinctRootEntity = includeDistinctRootEntity;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		this.setRowCount(
				this.dao.tamanhoDaLista(this.clazz, this.restrictions, this.aliases, this.includeDistinctRootEntity)
						.intValue());
		return this.dao.listarPorDemanda(this.clazz, Integer.valueOf(first), Integer.valueOf(pageSize),
				this.restrictions, this.projections, sortField, sortOrder, this.aliases,
				this.includeDistinctRootEntity);
	}
}