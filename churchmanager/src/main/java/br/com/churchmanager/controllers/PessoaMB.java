package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.PessoaBO;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.filter.PessoaFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class PessoaMB implements Serializable {
	
	private static final long serialVersionUID = 1654654455L;
	
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private MyLazyDataModel<Pessoa> pessoasLazy;
	private PessoaFilter pessoaFilter;
	
	@Inject
	private PessoaBO bo;

	@PostConstruct
	public void init() {
		Pessoa pessoa = (Pessoa) BuscaObjeto.comParametroGET(Pessoa.class, "id", this.bo);
		this.pessoa = pessoa;
	}

	public String salvar() {
		this.pessoa.atualizarIdMD5();
		this.pessoa.setDataCadastro(new Date());
		String matricula = bo.gerarMatricula(pessoa.getDataCadastro());
		this.pessoa.setMatricula(matricula);
		this.bo.salvar(this.pessoa);
		FacesUtil.informacao("pessoa-msg", "Cadastro com sucesso!", this.pessoa.toString());
		FacesUtil.atualizaComponente("pessoa-msg");
		this.pessoa = null;
		return null;
	}

	public String atualizar() {
		this.bo.atualizar(this.pessoa);
		FacesUtil.informacao("pessoa-msg", "Editado com sucesso!", this.pessoa.toString());
		FacesUtil.atualizaComponente("pessoa-msg");
		FacesUtil.manterMensagem();
		this.pessoa = null;
		return "/list/pessoa?faces-redirect=true";
	}

	public String filtrar() {
		this.pessoasLazy = this.bo.filtrar(this.pessoaFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.pessoa);
		this.pessoa = null;
		return null;
	}

	public List<Pessoa> perfis() {
		return this.bo.listar();
	}

	public Pessoa getPessoa() {
		if (this.pessoa == null) {
			this.pessoa = new Pessoa();
		}

		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		if (this.pessoas == null) {
			this.pessoas = new ArrayList<>();
		}

		return this.pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public MyLazyDataModel<Pessoa> getPessoasLazy() {
		if (this.pessoasLazy == null) {
			this.pessoasLazy = this.bo.filtrar(this.getPessoaFilter());
		}

		return this.pessoasLazy;
	}

	public void setPessoasLazy(MyLazyDataModel<Pessoa> pessoasLazy) {
		this.pessoasLazy = pessoasLazy;
	}

	public PessoaFilter getPessoaFilter() {
		if (this.pessoaFilter == null) {
			this.pessoaFilter = new PessoaFilter();
		}

		return this.pessoaFilter;
	}

	public void setPessoaFilter(PessoaFilter pessoaFilter) {
		this.pessoaFilter = pessoaFilter;
	}
}