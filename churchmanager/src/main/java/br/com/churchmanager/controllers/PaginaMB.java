package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
import br.com.churchmanager.model.Pagina;
import br.com.churchmanager.model.filter.PaginaFilter;
import br.com.churchmanager.report.GenericReport;
import br.com.churchmanager.service.PaginaService;

@Named
@ViewScoped
public class PaginaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pagina pagina;
	private List<Pagina> paginas;
	private LazyDataModel<Pagina> paginasLazy;
	private PaginaFilter paginaFilter;
	private static final Logger LOGGER = Logger.getLogger(GenericReport.class.getName());

	@Inject
	private PaginaService service;

	@Inject
	private FacesUtil facesUtil;
	
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		Pagina pagina = null;
		this.pagina = pagina;
	}

	public String salvar() {
		try {
			this.service.save(this.pagina);
			msgs.addInfo().cadastradoComSucesso();
			this.pagina = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			LOGGER.info(e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.service.save(this.pagina);
			msgs.addInfo().editadoComSucesso();
			this.pagina = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			LOGGER.info(e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/pagina?faces-redirect=true";
	}

	public String filtrar() {
		this.paginasLazy = this.service.lazyList(this.paginaFilter);
		return null;
	}

	public String deletar() {
		this.service.delete(this.pagina);
		this.pagina = null;
		return null;
	}

	private List<Pagina> listarPaginas;

	public List<Pagina> listarPaginas() {
		if (listarPaginas == null && facesUtil.isNotPostback()) {
			listarPaginas = this.service.findAll();
		}
		return listarPaginas;
	}

	public Pagina getPagina() {
		if (this.pagina == null) {
			this.pagina = new Pagina();
		}

		return this.pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	public List<Pagina> getPaginas() {
		if (this.paginas == null) {
			this.paginas = new ArrayList<>();
		}

		return this.paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public LazyDataModel<Pagina> getPaginasLazy() {
		if (this.paginasLazy == null) {
			this.paginasLazy = this.service.lazyList(this.paginaFilter);
		}

		return this.paginasLazy;
	}

	public void setPaginasLazy(LazyDataModel<Pagina> paginasLazy) {
		this.paginasLazy = paginasLazy;
	}

	public PaginaFilter getPaginaFilter() {
		if (this.paginaFilter == null) {
			this.paginaFilter = new PaginaFilter();
		}

		return this.paginaFilter;
	}

	public void setPaginaFilter(PaginaFilter paginaFilter) {
		this.paginaFilter = paginaFilter;
	}

	public List<Pagina> getListarPaginas() {
		return listarPaginas;
	}

	public void setListarPaginas(List<Pagina> listarPaginas) {
		this.listarPaginas = listarPaginas;
	}

}