package br.com.churchmanager.repository;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.CategoriaMovimentacao;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository
public interface CategoriaMovimentacaoRepository extends EntityRepository<CategoriaMovimentacao, Long> {

	LazyDataModel<CategoriaMovimentacao> lazyList(Filter filter);

	List<CategoriaMovimentacao> autoComplete(String value);

}