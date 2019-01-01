package br.com.churchmanager.service;

import java.util.List;

import br.com.churchmanager.model.Evento;
import br.com.churchmanager.model.filter.EventoFilter;

public interface EventoService extends Service<Evento> {

	List<Evento> eventosDoMes(EventoFilter filter);

}
