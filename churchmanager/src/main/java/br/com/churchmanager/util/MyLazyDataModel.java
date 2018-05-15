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
	private List<Criterion> restrictions = null;
	private List<Projection> projections = null;
	private List<Alias> aliases = null;
	private Boolean includeDistinctRootEntity = null;
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

	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		this.setRowCount(
				this.dao.tamanhoDaLista(this.clazz, this.restrictions, this.aliases, this.includeDistinctRootEntity)
						.intValue());
		List<T> list = this.dao.listarPorDemanda(this.clazz, Integer.valueOf(first), Integer.valueOf(pageSize),
				this.restrictions, this.projections, sortField, sortOrder, this.aliases,
				this.includeDistinctRootEntity);
		return list;
	}
}