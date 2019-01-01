package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Perfil;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository
public interface PerfilRepository extends EntityRepository<Perfil, Long> {

	LazyDataModel<Perfil> filtrar(PerfilRepository repository);

}