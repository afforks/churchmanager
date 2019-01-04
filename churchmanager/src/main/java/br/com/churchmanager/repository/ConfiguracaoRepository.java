package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Configuracao;

@Repository
public interface ConfiguracaoRepository extends EntityRepository<Configuracao, Long> {

	public LazyDataModel<Configuracao> filtrar(ConfiguracaoRepository repository);

}
