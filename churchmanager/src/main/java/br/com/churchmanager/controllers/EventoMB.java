package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.jsf.api.message.JsfMessage;

import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.jsf.FacesUtil;
import br.com.churchmanager.jsf.Msgs;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Evento;
import br.com.churchmanager.model.filter.EventoFilter;
import br.com.churchmanager.report.GenericReport;
import br.com.churchmanager.service.EventoService;
import br.com.churchmanager.util.BuscarArquivo;

@Named
@ViewScoped
public class EventoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Evento evento;
	private List<Evento> eventos;
	private LazyDataModel<Evento> eventosLazy;
	private EventoFilter eventoFilter;
	@Inject
	private EventoService service;
	@Inject
	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		Evento evento = null;
		this.evento = evento;
	}

	public String salvar() {
		try {
			this.service.save(this.evento);
			msgs.addInfo().cadastradoComSucesso();
			facesUtil.atualizarComponente("msg");
			this.evento = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.service.save(this.evento);
			msgs.addInfo().cadastradoComSucesso();
			this.evento = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/evento?faces-redirect=true";
	}

	public String filtrar() {
		this.eventosLazy = this.service.lazyList(this.eventoFilter);
		return null;
	}

	public String deletar() {
		this.service.delete(this.evento);
		this.evento = null;
		return null;
	}

	public List<Evento> perfis() {
		return this.service.findAll();
	}

	public void relatorioDeEventos() {
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("LOGO", BuscarArquivo.porCaminho("/resources/img/Logoic.jpg"));
		GenericReport.gerarPdfComConnection(mapa, "eventos", "eventos", true);
	}

	public Evento getEvento() {
		if (this.evento == null) {
			this.evento = new Evento();
		}

		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		if (this.eventos == null) {
			this.eventos = new ArrayList<>();
		}

		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public LazyDataModel<Evento> getEventosLazy() {
		if (this.eventosLazy == null) {
			this.eventosLazy = this.service.lazyList(this.eventoFilter);
		}

		return this.eventosLazy;
	}

	public void setEventosLazy(LazyDataModel<Evento> eventosLazy) {
		this.eventosLazy = eventosLazy;
	}

	public EventoFilter getEventoFilter() {
		if (this.eventoFilter == null) {
			this.eventoFilter = new EventoFilter();
		}

		return this.eventoFilter;
	}

	public void setEventoFilter(EventoFilter eventoFilter) {
		this.eventoFilter = eventoFilter;
	}
}