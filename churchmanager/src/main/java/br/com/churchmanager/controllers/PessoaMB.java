package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.Email;

import br.com.churchmanager.bo.PessoaBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.filter.PessoaFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.custom.Telefone;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class PessoaMB implements Serializable {

	private static final long serialVersionUID = 1654654455L;

	private Pessoa pessoa;
	private List<Pessoa> pessoas = new ArrayList<>();
	private MyLazyDataModel<Pessoa> pessoasLazy;
	private PessoaFilter pessoaFilter = new PessoaFilter();

	@Telefone
	private String telefone;

	@Email
	private String email;

	@Inject
	private PessoaBO bo;

	@PostConstruct
	public void init() {
		Pessoa pessoa = BuscaObjeto.comParametroGET("id", this.bo);
		this.pessoa = pessoa == null ? new Pessoa() : pessoa;
	}

	public String salvar() {
		try {
			this.pessoa.setDataCadastro(new Date());
			this.pessoa.gerarMatricula();
			this.bo.salvar(this.pessoa);
			FacesUtil.informacao("pessoa-msg", "Cadastrado com sucesso!", this.pessoa.toString());
			FacesUtil.atualizaComponente("pessoa-msg");
			this.pessoa = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("pessoa-msg", "Atenção!", e.getMessage());

		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("pessoa-msg", "Atenção!", e.getMessage());

		} catch (DadosException e) {
			FacesUtil.atencao("pessoa-msg", "Atenção!", e.getMessage());

		} finally {
			FacesUtil.atualizaComponente("pessoa-msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.bo.atualizar(this.pessoa);
			FacesUtil.informacao("pessoa-msg", "Editado com sucesso!", this.pessoa.toString());
			this.pessoa = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("pessoa-msg", "Atenção!", e.getMessage());

			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("pessoa-msg", "Atenção!", e.getMessage());

			return null;
		} catch (DadosException e) {
			FacesUtil.atencao("pessoa-msg", "Atenção!", e.getMessage());

			return null;
		} finally {
			FacesUtil.atualizaComponente("pessoa-msg");
			FacesUtil.manterMensagem();
		}
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

	public MyLazyDataModel<Pessoa> getPessoasLazy() {
		if (this.pessoasLazy == null) {
			this.pessoasLazy = this.bo.filtrar(this.getPessoaFilter());
		}
		return this.pessoasLazy;
	}

	// ***********************************

	public void resetarTelefone() {
		if (FacesUtil.naoFalhouNaValidacao()) {
			FacesUtil.informacao("msg-tel-email", "Telefone adicionado!", "Número: " + this.telefone);
			FacesUtil.atualizaComponente("msg-tel-email");
			this.telefone = null;
		}
	}

	public void resetarEmail() {
		if (FacesUtil.naoFalhouNaValidacao()) {
			FacesUtil.informacao("msg-tel-email", "E-mail adicionado!", "E-mail: " + this.email);
			FacesUtil.atualizaComponente("msg-tel-email");
			this.email = null;
		}
	}

	public Pessoa getPessoa() {
		return Objects.isNull(pessoa) ? new Pessoa() : pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public PessoaFilter getPessoaFilter() {
		return pessoaFilter;
	}

	public void setPessoaFilter(PessoaFilter pessoaFilter) {
		this.pessoaFilter = pessoaFilter;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPessoasLazy(MyLazyDataModel<Pessoa> pessoasLazy) {
		this.pessoasLazy = pessoasLazy;
	}

}