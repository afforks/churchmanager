package br.com.churchmanager.controllers;

import br.com.churchmanager.bo.DiretoriaBO;
import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.Diretoria;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.DiretoriaFilter;
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
public class DiretoriaMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Diretoria diretoria;
	private List<Diretoria> diretorias;
	private MyLazyDataModel<Diretoria> diretoriasLazy;
	private DiretoriaFilter diretoriaFilter;
	private Pessoa pessoa;
	private Cargo cargo;
	@Inject
	private DiretoriaBO bo;

	@PostConstruct
	public void init() {
		Diretoria diretoria = (Diretoria) BuscaObjeto.comParametroGET(Diretoria.class, "id", this.bo);
		this.diretoria = diretoria;
	}

	public String salvar() {
		this.bo.salvar(this.diretoria);
		FacesUtil.informacao("msg", "Cadastro com sucesso!", this.diretoria.toString());
		FacesUtil.atualizaComponenteDeMensagem("msg");
		this.diretoria = null;
		return null;
	}

	public String atualizar() {
		this.bo.atualizar(this.diretoria);
		FacesUtil.informacao("msg", "Editado com sucesso!", this.diretoria.toString());
		FacesUtil.atualizaComponenteDeMensagem("msg");
		FacesUtil.manterMensagem();
		this.diretoria = null;
		return "/list/diretoria?faces-redirect=true";
	}

	public String filtrar() {
		this.diretoriasLazy = this.bo.filtrar(this.diretoriaFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.diretoria);
		this.diretoria = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	public List<Diretoria> diretorias() {
		return this.bo.listar();
	}

	public Diretoria getDiretoria() {
		if (this.diretoria == null) {
			this.diretoria = new Diretoria();
		}

		return this.diretoria;
	}

	public void setDiretoria(Diretoria diretoria) {
		this.diretoria = diretoria;
	}

	public List<Diretoria> getDiretorias() {
		if (this.diretorias == null) {
			this.diretorias = new ArrayList<>();
		}

		return this.diretorias;
	}

	public void setDiretorias(List<Diretoria> diretorias) {
		this.diretorias = diretorias;
	}

	public MyLazyDataModel<Diretoria> getDiretoriasLazy() {
		if (this.diretoriasLazy == null) {
			this.diretoriasLazy = this.bo.filtrar(this.diretoriaFilter);
		}

		return this.diretoriasLazy;
	}

	public void setDiretoriasLazy(MyLazyDataModel<Diretoria> diretoriasLazy) {
		this.diretoriasLazy = diretoriasLazy;
	}

	public DiretoriaFilter getDiretoriaFilter() {
		if (this.diretoriaFilter == null) {
			this.diretoriaFilter = new DiretoriaFilter();
		}

		return this.diretoriaFilter;
	}

	public void setDiretoriaFilter(DiretoriaFilter diretoriaFilter) {
		this.diretoriaFilter = diretoriaFilter;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void removerPessoa() {
		this.pessoa = null;
	}

	public void removerCargo() {
		this.cargo = null;
	}
}