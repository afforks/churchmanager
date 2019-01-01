package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository
public interface CargoRepository extends EntityRepository<Cargo, Long> {

	LazyDataModel<Cargo> filtrar(CargoRepository repository);

}