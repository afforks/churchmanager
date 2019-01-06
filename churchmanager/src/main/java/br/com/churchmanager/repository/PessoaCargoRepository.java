package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.PessoaCargo;

@Repository
public interface PessoaCargoRepository extends EntityRepository<PessoaCargo, Long> {

}