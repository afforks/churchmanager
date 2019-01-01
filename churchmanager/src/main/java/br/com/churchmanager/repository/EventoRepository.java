package br.com.churchmanager.repository;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Evento;

@Repository
public interface EventoRepository extends EntityRepository<Evento, Long> {

	LazyDataModel<Evento> filtrar(EventoRepository repository);

	List<Evento> eventosDoMes();
	
}