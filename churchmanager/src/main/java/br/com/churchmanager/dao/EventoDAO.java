package br.com.churchmanager.dao;

import java.io.Serializable;
import java.util.List;

import br.com.churchmanager.dao.generic.DAO;
import br.com.churchmanager.model.Evento;
import br.com.churchmanager.model.filter.EventoFilter;

public class EventoDAO extends DAO<Evento> implements Serializable {
	private static final long serialVersionUID = 123145623331L;

	public EventoDAO() {
		super(Evento.class);
	}

	public List<Evento> eventosDoMes(EventoFilter filter) {
		return this.listarPorAtributosERestricoes("dia", true, filter.restricoes(), null);
	}
}