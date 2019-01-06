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
import br.com.churchmanager.model.CategoriaMovimentacao;
import br.com.churchmanager.model.filter.CategoriaMovimentacaoFilter;
import br.com.churchmanager.service.CategoriaMovimentacaoService;

@Named
@ViewScoped
public class CategoriaMovimentacaoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private CategoriaMovimentacao categoria;
	private List<CategoriaMovimentacao> categorias;
	private LazyDataModel<CategoriaMovimentacao> categoriasLazy;
	private CategoriaMovimentacaoFilter categoriaFilter;

	@Inject
	private CategoriaMovimentacaoService service;

	@Inject
	private FacesUtil facesUtil;

	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		CategoriaMovimentacao categoria = null;
		this.categoria = categoria;
	}

	public String salvar() {
		try {
			this.service.save(this.categoria);
			msgs.addInfo().cadastradoComSucesso();
			this.categoria = null;
		} catch (NegocioException e) {
			msgs.addWarn().atencao(e.getMessage());
		} catch (ViolacaoDeRestricaoException e) {
			msgs.addWarn().atencao(e.getMessage());
		} catch (DadosException e) {
			msgs.addWarn().atencao(e.getMessage());
		} finally {
			facesUtil.updateComponentes("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.service.save(this.categoria);
			msgs.addInfo().editadoComSucesso();
			this.categoria = null;
		} catch (NegocioException e) {
			msgs.addWarn().atencao(e.getMessage());
			return null;
		} catch (ViolacaoDeRestricaoException e) {
			msgs.addWarn().atencao(e.getMessage());
			return null;
		} catch (DadosException e) {
			msgs.addWarn().atencao(e.getMessage());
			return null;
		} finally {
			facesUtil.updateComponentes("msg");
		}
		return "/list/categoria?faces-redirect=true";
	}

	public String filtrar() {
		this.categoriasLazy = this.service.lazyList(this.categoriaFilter);
		return null;
	}

	public String deletar() {
		this.service.delete(this.categoria);
		this.categoria = null;
		return null;
	}

	public List<CategoriaMovimentacao> categorias() {
		return this.service.findAll();
	}

	public List<CategoriaMovimentacao> categoriasAutoComplete(String value) {
		return this.service.autoComplete(value);
	}

	public CategoriaMovimentacao getCategoriaMovimentacao() {
		if (this.categoria == null) {
			this.categoria = new CategoriaMovimentacao();
		}

		return this.categoria;
	}

	public void setCategoriaMovimentacao(CategoriaMovimentacao categoria) {
		this.categoria = categoria;
	}

	public List<CategoriaMovimentacao> getCategoriaMovimentacaos() {
		if (this.categorias == null) {
			this.categorias = new ArrayList<>();
		}

		return this.categorias;
	}

	public void setCategoriaMovimentacaos(List<CategoriaMovimentacao> categorias) {
		this.categorias = categorias;
	}

	public LazyDataModel<CategoriaMovimentacao> getCategoriaMovimentacaosLazy() {
		if(categoriasLazy == null) categoriasLazy = service.lazyList(getCategoriaFilter());
		return this.categoriasLazy;
	}

	public void setCategoriaMovimentacaosLazy(LazyDataModel<CategoriaMovimentacao> categoriasLazy) {
		this.categoriasLazy = categoriasLazy;
	}

	public CategoriaMovimentacaoFilter getCategoriaMovimentacaoFilter() {
		if (this.categoriaFilter == null) {
			this.categoriaFilter = new CategoriaMovimentacaoFilter();
		}

		return this.categoriaFilter;
	}

	public void setCategoriaMovimentacaoFilter(CategoriaMovimentacaoFilter categoriaFilter) {
		this.categoriaFilter = categoriaFilter;
	}

	public CategoriaMovimentacao getCategoria() {
		return categoria;
	}

	public List<CategoriaMovimentacao> getCategorias() {
		return categorias;
	}

	public LazyDataModel<CategoriaMovimentacao> getCategoriasLazy() {
		return categoriasLazy;
	}

	public CategoriaMovimentacaoFilter getCategoriaFilter() {
		return categoriaFilter;
	}
}