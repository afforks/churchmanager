package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.impl.handler.EntityRepositoryHandler;

import br.com.churchmanager.model.Configuracao;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository
public class ConfiguracaoRepository extends EntityRepositoryHandler<Configuracao, Long> {

	public LazyDataModel<Configuracao> filtrar(ConfiguracaoRepository repository) {
		// TODO Auto-generated method stub
		return null;
	}

}
