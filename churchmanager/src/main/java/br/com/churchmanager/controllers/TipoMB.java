package br.com.churchmanager.controllers;

import br.com.churchmanager.bo.TipoBO;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.Tipo;
import br.com.churchmanager.model.filter.TipoFilter;
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
public class TipoMB implements Serializable {
	private static final long serialVersionUID = 19879234234L;
	private Tipo tipo;
	private List<Tipo> tipos;
	private MyLazyDataModel<Tipo> tiposLazy;
	private TipoFilter tipoFilter;
	@Inject
	private TipoBO bo;

	@PostConstruct
	public void init() {
		Tipo tipo = (Tipo) BuscaObjeto.comParametroGET(Tipo.class, "id", this.bo);
		this.tipo = tipo;
	}

	public String salvar() {
		this.bo.salvar(this.tipo);
		FacesUtil.informacao("tipo-msg", "Cadastro com sucesso!", this.tipo.toString());
		FacesUtil.atualizaComponenteDeMensagem("tipo-msg");
		this.tipo = null;
		return null;
	}

	public String atualizar() {
		this.bo.atualizar(this.tipo);
		FacesUtil.informacao("tipo-msg", "Editado com sucesso!", this.tipo.toString());
		FacesUtil.atualizaComponenteDeMensagem("tipo-msg");
		FacesUtil.manterMensagem();
		this.tipo = null;
		return "/list/tipo?faces-redirect=true";
	}

	public String filtrar() {
		this.tiposLazy = this.bo.filtrar(this.tipoFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.tipo);
		this.tipo = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	public List<Tipo> tipos() {
		return this.bo.listar();
	}

	public Tipo getTipo() {
		if (this.tipo == null) {
			this.tipo = new Tipo();
		}

		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Tipo> getTipos() {
		if (this.tipos == null) {
			this.tipos = new ArrayList<>();
		}

		return this.tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public MyLazyDataModel<Tipo> getTiposLazy() {
		if (this.tiposLazy == null) {
			this.tiposLazy = this.bo.filtrar(this.tipoFilter);
		}

		return this.tiposLazy;
	}

	public void setTiposLazy(MyLazyDataModel<Tipo> tiposLazy) {
		this.tiposLazy = tiposLazy;
	}

	public TipoFilter getTipoFilter() {
		if (this.tipoFilter == null) {
			this.tipoFilter = new TipoFilter();
		}

		return this.tipoFilter;
	}

	public void setTipoFilter(TipoFilter tipoFilter) {
		this.tipoFilter = tipoFilter;
	}
}