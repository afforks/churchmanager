package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import br.com.churchmanager.model.Tipo;
import br.com.churchmanager.model.filter.TipoFilter;
import br.com.churchmanager.service.TipoService;

@Named
@ViewScoped
public class TipoMB implements Serializable {

	private static final long serialVersionUID = 19879234234L;

	private Tipo tipo;
	private List<Tipo> tipos;
	private LazyDataModel<Tipo> tiposLazy;
	private TipoFilter tipoFilter;

	@Inject
	private TipoService service;
	@Inject

	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		Tipo tipo = null;
		this.tipo = tipo;
	}

	public String salvar() {
		try {
			this.service.save(this.tipo);
			msgs.addInfo().cadastradoComSucesso();
			this.tipo = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.service.save(this.tipo);
			msgs.addInfo().editadoComSucesso();
			this.tipo = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/tipo?faces-redirect=true";
	}

	public String filtrar() {
		this.tiposLazy = this.service.lazyList(this.tipoFilter);
		return null;
	}

	public String deletar() {
		this.service.delete(this.tipo);
		this.tipo = null;
		return null;
	}

	public List<Tipo> tipos() {
		return this.service.findAll();
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

	public LazyDataModel<Tipo> getTiposLazy() {
		if (this.tiposLazy == null) {
			this.tiposLazy = this.service.lazyList(this.tipoFilter);
		}

		return this.tiposLazy;
	}

	public void setTiposLazy(LazyDataModel<Tipo> tiposLazy) {
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