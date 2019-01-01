package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.AtividadeEclesiastica;

@Repository(forEntity = AtividadeEclesiastica.class)
public interface AtividadeEclesiasticaRepository extends EntityRepository<AtividadeEclesiastica, Long> {

}