package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.PessoaCargoBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.PessoaCargo;
import br.com.churchmanager.model.filter.PessoaCargoFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class PessoaCargoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private PessoaCargo pessoaCargo;
	private List<PessoaCargo> pessoaCargos;
	private MyLazyDataModel<PessoaCargo> pessoaCargosLazy;
	private PessoaCargoFilter pessoaCargoFilter;
	@Inject
	private PessoaCargoBO bo;

	@PostConstruct
	public void init() {
		PessoaCargo pessoaCargo = BuscaObjeto.comParametroGET("id", this.bo);
		this.pessoaCargo = pessoaCargo;
	}

	public String salvar() {
		try {
			this.bo.salvar(this.pessoaCargo);
			FacesUtil.informacao("msg", "Cadastrado com sucesso!", this.pessoaCargo.toString());
			FacesUtil.atualizaComponente("msg-cad-pessoa-cargo");
			this.pessoaCargo = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

		} finally {
			FacesUtil.atualizaComponente("msg");
		}

		return null;
	}

	public String atualizar() {
		try {
			this.bo.atualizar(this.pessoaCargo);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.pessoaCargo.toString());
			pessoaCargo = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

			return null;
		} catch (DadosException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());

			return null;
		} finally {
			FacesUtil.atualizaComponente("msg");
			FacesUtil.manterMensagem();
		}

		return "/list/pessoaCargo?faces-redirect=true";
	}

	public String filtrar() {
		this.pessoaCargosLazy = this.bo.filtrar(this.pessoaCargoFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.pessoaCargo);
		this.pessoaCargo = null;
		return null;
	}

	public List<PessoaCargo> pessoaCargos() {
		return this.bo.listar();
	}

	public PessoaCargo getPessoaCargo() {
		if (this.pessoaCargo == null) {
			this.pessoaCargo = new PessoaCargo();
		}

		return this.pessoaCargo;
	}

	public void setPessoaCargo(PessoaCargo pessoaCargo) {
		this.pessoaCargo = pessoaCargo;
	}

	public List<PessoaCargo> getPessoaCargos() {
		if (this.pessoaCargos == null) {
			this.pessoaCargos = new ArrayList<>();
		}

		return this.pessoaCargos;
	}

	public void setPessoaCargos(List<PessoaCargo> pessoaCargos) {
		this.pessoaCargos = pessoaCargos;
	}

	public MyLazyDataModel<PessoaCargo> getPessoaCargosLazy() {
		if (this.pessoaCargosLazy == null) {
			this.pessoaCargosLazy = this.bo.filtrar(this.getPessoaCargoFilter());
		}

		return this.pessoaCargosLazy;
	}

	public void setPessoaCargosLazy(MyLazyDataModel<PessoaCargo> pessoaCargosLazy) {
		this.pessoaCargosLazy = pessoaCargosLazy;
	}

	public PessoaCargoFilter getPessoaCargoFilter() {
		if (this.pessoaCargoFilter == null) {
			this.pessoaCargoFilter = new PessoaCargoFilter();
		}

		return this.pessoaCargoFilter;
	}

	public void setPessoaCargoFilter(PessoaCargoFilter pessoaCargoFilter) {
		this.pessoaCargoFilter = pessoaCargoFilter;
	}

	public void removerPessoa() {
		this.getPessoaCargo().setPessoa((Pessoa) null);
	}

	public void removerCargo() {
		this.getPessoaCargo().setCargo((Cargo) null);
	}

	public void resetarCampos() {
		removerCargo();
		removerPessoa();
	}

}