package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.CategoriaMovimentacaoBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.CategoriaMovimentacao;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.CategoriaMovimentacaoFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class CategoriaMovimentacaoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private CategoriaMovimentacao categoria;
	private List<CategoriaMovimentacao> categorias;
	private MyLazyDataModel<CategoriaMovimentacao> categoriasLazy;
	private CategoriaMovimentacaoFilter categoriaFilter;
	@Inject
	private CategoriaMovimentacaoBO bo;

	@PostConstruct
	public void init() {
		CategoriaMovimentacao categoria = (CategoriaMovimentacao) BuscaObjeto
				.comParametroGET(CategoriaMovimentacao.class, "id", this.bo);
		this.categoria = categoria;
	}

	public String salvar() {
		try {
			this.bo.salvar(this.categoria);
			FacesUtil.informacao("msg", "Cadastro com sucesso!", this.categoria.toString());
			this.categoria = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", "O nome '"+categoria.getNome()+"' está duplicado, por favor, informe outro!");
			e.printStackTrace();
		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
		} finally {
			FacesUtil.atualizaComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.bo.atualizar(this.categoria);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.categoria.toString());
			this.categoria = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", "O nome '"+categoria.getNome()+"' está duplicado, por favor, informe outro!");
			e.printStackTrace();
			return null;
		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			FacesUtil.atualizaComponente("msg");
			FacesUtil.manterMensagem();
		}
		return "/list/categoria?faces-redirect=true";
	}

	public String filtrar() {
		this.categoriasLazy = this.bo.filtrar(this.categoriaFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.categoria);
		this.categoria = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	public List<CategoriaMovimentacao> categorias() {
		return this.bo.listar();
	}

	public List<CategoriaMovimentacao> categoriasAutoComplete(String value) {
		return this.bo.autoCompletar(value);
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

	public MyLazyDataModel<CategoriaMovimentacao> getCategoriaMovimentacaosLazy() {
		if (this.categoriasLazy == null) {
			this.categoriasLazy = this.bo.filtrar(this.categoriaFilter);
		}

		return this.categoriasLazy;
	}

	public void setCategoriaMovimentacaosLazy(MyLazyDataModel<CategoriaMovimentacao> categoriasLazy) {
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
}