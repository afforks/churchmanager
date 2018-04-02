package br.com.churchmanager.generic.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.EntidadeGenerica;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.Transacional;

public abstract class DAO<T extends EntidadeGenerica> implements Serializable {
	private static final long serialVersionUID = -3812355503300426470L;
	@Inject
	private EntityManager entityManager;
	private Class<T> clazz;

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public DAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Transacional
	public void salvar(T entity) throws ViolacaoDeRestricaoException, DadosException {
		try {
			this.entityManager.persist(entity);
		} catch (PersistenceException e) {
			System.out.println("------------------ STACKTRACE");
			e.printStackTrace();

			System.out.println("------------------ MENSAGEM");
			System.out.println(e.getMessage());
			
			System.out.println("------------------ LOCALIZED MENSAGEM");
			System.out.println(e.getLocalizedMessage());
			
			System.out.println("------------------ CAUSE MENSAGEM");
			System.out.println(e.getCause().getMessage());
			
			System.out.println("------------------ GET STACKTRACE");
			System.out.println(e.getCause().getStackTrace()[0]);
			
			if(e.getCause().toString().contains("ConstraintViolationException")) {
				throw new ViolacaoDeRestricaoException("A operação DML solicitada resultou em uma violação de uma restrição de integridade definida.");
			} else if(e.getCause().toString().contains("DataException")) {
				throw new DadosException("A avaliação da instrução SQL válida em relação aos dados fornecidos resultou em alguma operação ilegal, tipos incompatíveis ou cardinalidade incorreta.");
			}
		}
	}

	@Transacional
	public T atualizar(T entity)  throws ViolacaoDeRestricaoException, DadosException{
		T t = null;
		try {
			t = this.entityManager.merge(entity);
		} catch (PersistenceException e) {
			if(e.getCause().toString().contains("ConstraintViolationException")) {
				e.printStackTrace();
				throw new ViolacaoDeRestricaoException("A operação DML solicitada resultou em uma violação de uma restrição de integridade definida.");
			} else if(e.getCause().toString().contains("DataException")) {
				e.printStackTrace();
				throw new DadosException("A avaliação da instrução SQL válida em relação aos dados fornecidos resultou em alguma operação ilegal, tipos incompatíveis ou cardinalidade incorreta.");
			}
		}
		return t;
	}

	@Transacional
	public T buscarPorId(Serializable id) {
		return this.entityManager.find(this.clazz, id);
	}

	@Transacional
	public void excluir(Serializable id) {
		T entity = this.buscarPorId(id);
		this.entityManager.remove(entity);
	}

	@Transacional
	public void atualizarStatus(T t) throws ViolacaoDeRestricaoException,  DadosException {
		t.setStatus(Status.negarStatus(t.getStatus()));
		this.atualizar(t);
	}

	public T criarQuery(String query) {
		return this.entityManager.createQuery(query, this.clazz).getSingleResult();
	}

	public List<T> listarComNamedQuery(String query, Object[] parametros) {
		Query q = this.entityManager.createNamedQuery(query);

		for (int i = 0; i < parametros.length; i += 2) {
			q.setParameter(parametros[i].toString(), parametros[i + 1]);
		}

		return q.getResultList();
	}

	public List<T> listarComQueryNativa(String query, Object[] parametros) {
		Query q = this.entityManager.createNamedQuery(query);

		for (int i = 0; i < parametros.length; i += 2) {
			q.setParameter(parametros[i].toString(), parametros[i + 1]);
		}

		return q.getResultList();
	}

	public List<T> listar(boolean ordemAscendente, String... propertiersOrders) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery(this.clazz);
		Root root = cq.from(this.clazz);
		ArrayList orders = new ArrayList();
		String[] arg9 = propertiersOrders;
		int arg8 = propertiersOrders.length;

		for (int arg7 = 0; arg7 < arg8; ++arg7) {
			String p = arg9[arg7];
			if (ordemAscendente) {
				orders.add(builder.asc(root.get(p)));
			} else {
				orders.add(builder.desc(root.get(p)));
			}
		}

		cq.orderBy(orders);
		return this.entityManager.createQuery(cq).getResultList();
	}

	public List<T> listarAutocompletar(String orderBy, boolean orderAsc, String propertyName, String value) {
		ArrayList restricoes = new ArrayList();
		if (value != null && !value.isEmpty()) {
			restricoes.add(Restrictions.like(propertyName, value, MatchMode.ANYWHERE));
		}

		return this.listarPorAtributosERestricoes(orderBy, orderAsc, restricoes, (Collection) null);
	}

	public T buscarPorAtributo(String chave, Object valor) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery(this.clazz);
		Root root = cq.from(this.clazz);
		Predicate predicate = builder.equal(root.get(chave), valor);
		cq.select(root);
		cq.where(predicate);
		TypedQuery query = this.entityManager.createQuery(cq);
		return (T) query.getSingleResult();
	}

	public T buscarPorAtributo(String... args) {
		return null;
	}

	public Double somatorio(String propertyName, List<?> restricoes, List<Alias> aliases,
			Boolean includeDistinctRootEntity) {
		Criteria criteria = this.criteria(restricoes, aliases, includeDistinctRootEntity);
		criteria.setProjection(Projections.sum(propertyName));
		Double total = (Double) criteria.uniqueResult();
		return total;
	}

	public Long contagem(String propertyName, List<?> restricoes, List<Alias> aliases,
			Boolean includeDistinctRootEntity) {
		Criteria criteria = this.criteria(restricoes, aliases, includeDistinctRootEntity);
		criteria.setProjection(Projections.count(propertyName));
		Long total = (Long) criteria.uniqueResult();
		return total;
	}

	public Number max(String propertyName, List<?> restricoes, List<Alias> aliases, Boolean includeDistinctRootEntity) {
		Criteria criteria = this.criteria(restricoes, aliases, includeDistinctRootEntity);
		criteria.setProjection(Projections.max(propertyName));
		Number total = (Number) criteria.uniqueResult();
		return total;
	}

	public Number min(String propertyName, List<?> restricoes, List<Alias> aliases, Boolean includeDistinctRootEntity) {
		Criteria criteria = this.criteria(restricoes, aliases, includeDistinctRootEntity);
		criteria.setProjection(Projections.min(propertyName));
		Number total = (Number) criteria.uniqueResult();
		return total;
	}

	public Number avg(String propertyName, List<?> restricoes, List<Alias> aliases, Boolean includeDistinctRootEntity) {
		Criteria criteria = this.criteria(restricoes, aliases, includeDistinctRootEntity);
		criteria.setProjection(Projections.avg(propertyName));
		Number total = (Number) criteria.uniqueResult();
		return total;
	}

	private Criteria criteria(List<?> restricoes, List<Alias> aliases, Boolean includeDistinctRootEntity) {
		Criteria criteria = null;

		try {
			Session e = (Session) this.entityManager.unwrap(Session.class);
			criteria = e.createCriteria(this.clazz);
			if (includeDistinctRootEntity != null && includeDistinctRootEntity.booleanValue()) {
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			}

			if (aliases != null && !aliases.isEmpty()) {
				Iterator criterion = aliases.iterator();

				while (criterion.hasNext()) {
					Alias iterator1 = (Alias) criterion.next();
					if (iterator1.getTipoDeJoin() != null) {
						criteria.createAlias(iterator1.getAtributo(), iterator1.getAliasAtributo(),
								iterator1.getTipoDeJoin());
					} else {
						criteria.createAlias(iterator1.getAtributo(), iterator1.getAliasAtributo());
					}
				}
			}

			if (restricoes != null && restricoes != null) {
				Iterator iterator11 = restricoes.iterator();

				while (iterator11.hasNext()) {
					Criterion criterion1 = (Criterion) iterator11.next();
					criteria.add(criterion1);
				}
			}
		} catch (HibernateException arg7) {
			arg7.printStackTrace();
		}

		return criteria;
	}

	public Long tamanhoDaLista(Class<?> clazz, List<?> restricoes, List<Alias> aliases,
			Boolean includeDistinctRootEntity) {
		Long size = null;

		try {
			Session e = (Session) this.entityManager.unwrap(Session.class);
			Criteria criteria = e.createCriteria(clazz);
			if (includeDistinctRootEntity != null && includeDistinctRootEntity.booleanValue()) {
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			}

			if (aliases != null && !aliases.isEmpty()) {
				Iterator transaction = aliases.iterator();

				while (transaction.hasNext()) {
					Alias iterator1 = (Alias) transaction.next();
					if (iterator1.getTipoDeJoin() != null) {
						criteria.createAlias(iterator1.getAtributo(), iterator1.getAliasAtributo(),
								iterator1.getTipoDeJoin());
					} else {
						criteria.createAlias(iterator1.getAtributo(), iterator1.getAliasAtributo());
					}
				}
			}

			if (restricoes != null && restricoes != null) {
				Iterator iterator11 = restricoes.iterator();

				while (iterator11.hasNext()) {
					Criterion transaction1 = (Criterion) iterator11.next();
					criteria.add(transaction1);
				}
			}

			criteria.setProjection(Projections.rowCount());
			size = (Long) criteria.uniqueResult();
			Transaction transaction2 = e.beginTransaction();
			transaction2.commit();
		} catch (HibernateException arg9) {
			arg9.printStackTrace();
		}

		return size;
	}

	public List<T> listarPorDemanda(Class<T> clazz, Integer startPage, Integer maxPage, List<Criterion> restricoes,
			List<Projection> projecoes, String campoOrdenacao, SortOrder tipoOrdem, List<Alias> aliases,
			Boolean includeDistinctRootEntity) {
		List list = null;

		try {
			Session e = (Session) this.entityManager.unwrap(Session.class);
			Criteria criteria = e.createCriteria(clazz);
			if (includeDistinctRootEntity != null && includeDistinctRootEntity.booleanValue()) {
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			}

			if (aliases != null && !aliases.isEmpty()) {
				Iterator transaction = aliases.iterator();

				while (transaction.hasNext()) {
					Alias iterator2 = (Alias) transaction.next();
					if (iterator2.getTipoDeJoin() != null) {
						criteria.createAlias(iterator2.getAtributo(), iterator2.getAliasAtributo(),
								iterator2.getTipoDeJoin());
					} else {
						criteria.createAlias(iterator2.getAtributo(), iterator2.getAliasAtributo());
					}
				}
			}

			Iterator iterator21;
			if (restricoes != null && !restricoes.isEmpty()) {
				iterator21 = restricoes.iterator();

				while (iterator21.hasNext()) {
					Criterion transaction1 = (Criterion) iterator21.next();
					criteria.add(transaction1);
				}
			}

			if (projecoes != null && !projecoes.isEmpty()) {
				iterator21 = projecoes.iterator();

				while (iterator21.hasNext()) {
					Projection transaction2 = (Projection) iterator21.next();
					criteria.setProjection(transaction2);
				}
			}

			if (campoOrdenacao != null && tipoOrdem != null) {
				criteria.addOrder(
						tipoOrdem.equals(SortOrder.ASCENDING) ? Order.asc(campoOrdenacao) : Order.desc(campoOrdenacao));
			}

			criteria.setFirstResult(startPage.intValue());
			criteria.setMaxResults(maxPage.intValue());
			list = criteria.list();
			Transaction transaction3 = e.beginTransaction();
			transaction3.commit();
		} catch (HibernateException arg14) {
			arg14.printStackTrace();
		}

		return list;
	}

	public List<T> listarPorAtributosERestricoes(String orderBy, boolean orderAsc, List<Criterion> restrictions,
			Collection<Alias> aliases) {
		List list = null;

		try {
			Session e = (Session) this.entityManager.unwrap(Session.class);
			Transaction transaction = e.beginTransaction();
			Criteria criteria = e.createCriteria(this.clazz);
			if (aliases != null && !aliases.isEmpty()) {
				Iterator i = aliases.iterator();
				Alias aux = null;

				while (i.hasNext()) {
					aux = (Alias) i.next();
					if (aux.getTipoDeJoin() != null) {
						criteria.createAlias(aux.getAtributo(), aux.getAliasAtributo(), aux.getTipoDeJoin());
					} else {
						criteria.createAlias(aux.getAtributo(), aux.getAliasAtributo());
					}
				}
			}

			if (orderBy != null) {
				criteria.addOrder(orderAsc ? Order.asc(orderBy) : Order.desc(orderBy));
			}

			if (restrictions != null && !restrictions.isEmpty()) {
				for (int arg11 = 0; arg11 < restrictions.size(); ++arg11) {
					criteria.add((Criterion) restrictions.get(arg11));
				}
			}

			list = criteria.list();
			transaction.commit();
		} catch (HibernateException arg10) {
			arg10.printStackTrace();
		}

		return list;
	}

	public T buscarPorAtributosERestricoes(Class<T> clazz, List<Criterion> restrictions, Collection<Alias> aliases) {
		EntidadeGenerica t = null;

		try {
			Session e = (Session) this.entityManager.unwrap(Session.class);
			Criteria criteria = e.createCriteria(clazz);
			if (aliases != null && !aliases.isEmpty()) {
				Iterator i = aliases.iterator();
				Alias aux = null;

				while (i.hasNext()) {
					aux = (Alias) i.next();
					if (aux.getTipoDeJoin() != null) {
						criteria.createAlias(aux.getAtributo(), aux.getAliasAtributo(), aux.getTipoDeJoin());
					} else {
						criteria.createAlias(aux.getAtributo(), aux.getAliasAtributo());
					}
				}
			}

			if (restrictions != null && !restrictions.isEmpty()) {
				for (int arg9 = 0; arg9 < restrictions.size(); ++arg9) {
					criteria.add((Criterion) restrictions.get(arg9));
				}
			}

			t = (EntidadeGenerica) criteria.uniqueResult();
		} catch (HibernateException arg8) {
			arg8.printStackTrace();
		}

		return (T) t;
	}

	public MyLazyDataModel<T> filtrar(DAO<T> dao, Filter filter) {
		return new MyLazyDataModel(dao, this.clazz, filter.restricoes(), filter.projecoes(), filter.aliases(),
				filter.usarDistinct());
	}
}