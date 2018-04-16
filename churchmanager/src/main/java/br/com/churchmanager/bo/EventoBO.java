package br.com.churchmanager.bo;

import br.com.churchmanager.dao.EventoDAO;
import br.com.churchmanager.generic.dao.Buscador;
import br.com.churchmanager.model.Evento;
import br.com.churchmanager.model.filter.EventoFilter;
import br.com.churchmanager.util.MyLazyDataModel;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class EventoBO implements Serializable, Buscador<Evento> {
	private static final long serialVersionUID = 1L;

	private static final boolean ORDER_ASC = true;

	@Inject
	EventoDAO dao;

	public void salvar(Evento evento) {
		this.validar(evento);
		this.dao.salvar(evento);
	}

	public void atualizar(Evento evento) {
		this.validar(evento);
		this.dao.atualizar(evento);
	}

	public void deletar(Evento evento) {
		this.dao.excluir(evento);
	}

	public List<Evento> listar() {
		return this.dao.listar(ORDER_ASC);
	}

	public void validar(Evento evento) {
	}

	public Evento buscarPorId(Serializable id) {
		return (Evento) this.dao.buscarPorId(id);
	}

	public MyLazyDataModel<Evento> filtrar(EventoFilter eventoFilter) {
		return this.dao.filtrar(this.dao, eventoFilter);
	}

	public List<Evento> eventosDoMes(EventoFilter filter) {
		return this.dao.eventosDoMes(filter);
	}
}