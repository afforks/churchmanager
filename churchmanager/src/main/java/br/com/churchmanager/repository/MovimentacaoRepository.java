package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends EntityRepository<Movimentacao, Long> {

}