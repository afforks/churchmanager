package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Tipo;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository
public interface TipoRepository extends EntityRepository<Tipo, Long> {

	LazyDataModel<Tipo> filtrar(TipoRepository repository);

}