package br.com.churchmanager.repository;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.model.AtividadeEclesiastica;

public interface MyEntityRepository<T> extends EntityRepository<T, Long> {

	List<AtividadeEclesiastica> findByNomeLike(String nome);

}
