package br.com.churchmanager.controllers;

import br.com.churchmanager.bo.PaginaBO;
import br.com.churchmanager.model.Pagina;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.PaginaFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PaginaMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pagina pagina;
	private List<Pagina> paginas;
	private MyLazyDataModel<Pagina> paginasLazy;
	private PaginaFilter paginaFilter;
	@Inject
	private PaginaBO bo;

	@PostConstruct
	public void init() {
		Pagina pagina = (Pagina) BuscaObjeto.comParametroGET(Pagina.class, "id", this.bo);
		this.pagina = pagina;
	}

	public String salvar() {
		this.bo.salvar(this.pagina);
		FacesUtil.informacao("msg", "Cadastro com sucesso!", this.pagina.toString());
		FacesUtil.atualizaComponenteDeMensagem("msg");
		this.pagina = null;
		return null;
	}

	public String atualizar() {
		this.bo.atualizar(this.pagina);
		FacesUtil.informacao("msg", "Editado com sucesso!", this.pagina.toString());
		FacesUtil.atualizaComponenteDeMensagem("msg");
		FacesUtil.manterMensagem();
		this.pagina = null;
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

	public List<Pagina> paginas() {
		return this.bo.listar();
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