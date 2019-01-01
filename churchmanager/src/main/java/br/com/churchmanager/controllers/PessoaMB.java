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

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.hibernate.validator.constraints.Email;

import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.jsf.FacesUtil;
import br.com.churchmanager.jsf.Msgs;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.filter.PessoaFilter;
import br.com.churchmanager.service.PessoaService;
import br.com.churchmanager.util.custom.Telefone;

@Named
@ViewScoped
public class PessoaMB implements Serializable {

	private static final long serialVersionUID = 1654654455L;

	private Pessoa pessoa;
	private List<Pessoa> pessoas = new ArrayList<>();
	private LazyDataModel<Pessoa> pessoasLazy;
	private PessoaFilter pessoaFilter = new PessoaFilter();

	@Telefone
	private String telefone;

	@Email
	private String email;

	@Inject
	private PessoaService bo;

	@Inject
	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		Pessoa pessoa = null;
		this.pessoa = pessoa == null ? new Pessoa() : pessoa;
	}

	public String salvar() {
		try {
			this.pessoa.setDataCadastro(new Date());
			this.pessoa.gerarMatricula();
			this.bo.save(this.pessoa);
			msgs.addInfo().cadastradoComSucesso();
			this.pessoa = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("pessoa-msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.bo.save(this.pessoa);
			msgs.addInfo().editadoComSucesso();
			this.pessoa = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("pessoa-msg");
			facesUtil.manterMensagem();
		}
		return "/list/pessoa?faces-redirect=true";
	}

	public String filtrar() {
		this.pessoasLazy = this.bo.lazyList(this.pessoaFilter);
		return null;
	}

	public String deletar() {
		this.bo.delete(this.pessoa);
		this.pessoa = null;
		return null;
	}

	public LazyDataModel<Pessoa> getPessoasLazy() {
		if (this.pessoasLazy == null) {
			this.pessoasLazy = this.bo.lazyList(this.getPessoaFilter());
		}
		return this.pessoasLazy;
	}

	// ***********************************

	public void resetarTelefone() {
		if (facesUtil.naoFalhouNaValidacao()) {
			msgs.addInfo().info("Telefone adicionado!", "Número: " + this.telefone);
			facesUtil.atualizarComponente("msg-tel-email");
			this.telefone = null;
		}
	}

	public void resetarEmail() {
		if (facesUtil.naoFalhouNaValidacao()) {
			msgs.addInfo().info("E-mail adicionado!", "E-mail: " + this.email);
			facesUtil.atualizarComponente("msg-tel-email");
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

	public void setPessoasLazy(LazyDataModel<Pessoa> pessoasLazy) {
		this.pessoasLazy = pessoasLazy;
	}

}