package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Perfil;

@Repository
public interface PerfilRepository extends EntityRepository<Perfil, Long> {

}