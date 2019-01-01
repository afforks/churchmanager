package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.PessoaCargo;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository
public interface PessoaCargoRepository extends EntityRepository<PessoaCargo, Long> {

	LazyDataModel<PessoaCargo> filtrar(PessoaCargoRepository repository);
}