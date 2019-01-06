package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Pagina;

@Repository
public interface PaginaRepository extends EntityRepository<Pagina, Long> {

}