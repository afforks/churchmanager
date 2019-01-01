package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Patrimonio;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository
public interface PatrimonioRepository extends EntityRepository<Patrimonio, Long> {

	LazyDataModel<Patrimonio> filtrar(PatrimonioRepository repository);

}