package br.com.churchmanager.dao;

import br.com.churchmanager.generic.dao.DAO;
import br.com.churchmanager.model.Evento;
import br.com.churchmanager.model.filter.EventoFilter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class EventoDAO extends DAO<Evento> implements Serializable {
	private static final long serialVersionUID = 123123331L;

	public EventoDAO() {
		super(Evento.class);
	}

	public List<Evento> eventosDoMes(EventoFilter filter) {
		return this.listarPorAtributosERestricoes("dia", true, filter.restricoes(), (Collection) null);
	}
}