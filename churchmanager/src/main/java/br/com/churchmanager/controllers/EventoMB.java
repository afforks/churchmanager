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

import br.com.churchmanager.bo.EventoBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Evento;
import br.com.churchmanager.model.filter.EventoFilter;
import br.com.churchmanager.report.GenericReport;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.BuscarArquivo;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class EventoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Evento evento;
	private List<Evento> eventos;
	private MyLazyDataModel<Evento> eventosLazy;
	private EventoFilter eventoFilter;
	@Inject
	private EventoBO bo;

	@PostConstruct
	public void init() {
		Evento evento = BuscaObjeto.comParametroGET("id", this.bo);
		this.evento = evento;
	}

	public String salvar() {
		try {
			this.bo.salvar(this.evento);
			FacesUtil.informacao("msg", "Cadastrado com sucesso!", this.evento.toString());
			FacesUtil.atualizaComponente("msg");
			this.evento = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!",
					"O nome '" + evento.getNome() + "' está duplicado, por favor, informe outro!");

		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

		} finally {
			FacesUtil.atualizaComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.bo.atualizar(this.evento);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.evento.toString());
			this.evento = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!",
					"O nome '" + evento.getNome() + "' está duplicado, por favor, informe outro!");

			return null;
		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

			return null;
		} finally {
			FacesUtil.atualizaComponente("msg");
			FacesUtil.manterMensagem();
		}
		return "/list/evento?faces-redirect=true";
	}

	public String filtrar() {
		this.eventosLazy = this.bo.filtrar(this.eventoFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.evento);
		this.evento = null;
		return null;
	}

	public List<Evento> perfis() {
		return this.bo.listar();
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

	public MyLazyDataModel<Evento> getEventosLazy() {
		if (this.eventosLazy == null) {
			this.eventosLazy = this.bo.filtrar(this.eventoFilter);
		}

		return this.eventosLazy;
	}

	public void setEventosLazy(MyLazyDataModel<Evento> eventosLazy) {
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