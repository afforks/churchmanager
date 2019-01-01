package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Diretoria;

@Repository
public interface DiretoriaRepository extends EntityRepository<Diretoria, Long>{

	LazyDataModel<Diretoria> filtrar(DiretoriaRepository repository);
	
}