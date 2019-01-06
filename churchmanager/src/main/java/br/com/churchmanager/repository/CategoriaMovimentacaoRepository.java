package br.com.churchmanager.repository;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.CategoriaMovimentacao;

@Repository
public interface CategoriaMovimentacaoRepository extends EntityRepository<CategoriaMovimentacao, Long> {

	List<CategoriaMovimentacao> findByNomeLike(String nome);

}