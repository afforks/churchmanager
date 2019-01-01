package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Movimentacao;
import br.com.churchmanager.model.filter.MovimentacaoFilter;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository
public interface MovimentacaoRepository extends EntityRepository<Movimentacao, Long> {

	LazyDataModel<Movimentacao> filtrar(MovimentacaoRepository repository, MovimentacaoFilter movimentacaoFilter);

}