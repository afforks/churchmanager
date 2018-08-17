package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.PaginaBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Pagina;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.PaginaFilter;
import br.com.churchmanager.report.GenericReport;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class PaginaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pagina pagina;
	private List<Pagina> paginas;
	private MyLazyDataModel<Pagina> paginasLazy;
	private PaginaFilter paginaFilter;
	private static final Logger LOGGER = Logger.getLogger(GenericReport.class.getName());

	@Inject
	private PaginaBO bo;

	@PostConstruct
	public void init() {
		Pagina pagina = BuscaObjeto.comParametroGET("id", this.bo);
		this.pagina = pagina;
	}

	public String salvar() {
		try {
			this.bo.salvar(this.pagina);
			FacesUtil.informacao("msg", "Cadastrado com sucesso!", this.pagina.toString());
			FacesUtil.atualizaComponente("msg");
			this.pagina = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			LOGGER.info(e.getMessage());
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!",
					"O valor '" + pagina.getNome() + "' está duplicado, por favor, informe outro!");
			LOGGER.info(e.getMessage());
		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			LOGGER.info(e.getMessage());
		} finally {
			FacesUtil.atualizaComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.bo.atualizar(this.pagina);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.pagina.toString());
			this.pagina = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			LOGGER.info(e.getMessage());
			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!",
					"O valor '" + pagina.getNome() + "' está duplicado, por favor, informe outro!");
			LOGGER.info(e.getMessage());
			return null;
		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			LOGGER.info(e.getMessage());
			return null;
		} finally {
			FacesUtil.atualizaComponente("msg");
			FacesUtil.manterMensagem();
		}
		return "/list/pagina?faces-redirect=true";
	}

	public String filtrar() {
		this.paginasLazy = this.bo.filtrar(this.paginaFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.pagina);
		this.pagina = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	private List<Pagina> listarPaginas;

	public List<Pagina> listarPaginas() {
		if (listarPaginas == null && FacesUtil.isNotPostback()) {
			listarPaginas = this.bo.listar();
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

	public MyLazyDataModel<Pagina> getPaginasLazy() {
		if (this.paginasLazy == null) {
			this.paginasLazy = this.bo.filtrar(this.paginaFilter);
		}

		return this.paginasLazy;
	}

	public void setPaginasLazy(MyLazyDataModel<Pagina> paginasLazy) {
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
}