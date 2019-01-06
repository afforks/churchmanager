package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Diretoria;

@Repository
public interface DiretoriaRepository extends EntityRepository<Diretoria, Long>{

}