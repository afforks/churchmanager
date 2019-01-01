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
import br.com.churchmanager.model.Cargo;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.PessoaCargo;
import br.com.churchmanager.model.filter.PessoaCargoFilter;
import br.com.churchmanager.service.PessoaCargoService;

@Named
@ViewScoped
public class PessoaCargoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private PessoaCargo pessoaCargo;
	private List<PessoaCargo> pessoaCargos;
	private LazyDataModel<PessoaCargo> pessoaCargosLazy;
	private PessoaCargoFilter pessoaCargoFilter;
	@Inject
	private PessoaCargoService bo;
	@Inject
	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		PessoaCargo pessoaCargo = null;
		this.pessoaCargo = pessoaCargo;
	}

	public String salvar() {
		try {
			this.bo.save(this.pessoaCargo);
			msgs.addInfo().cadastradoComSucesso();
			this.pessoaCargo = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());

		} finally {
			facesUtil.atualizarComponente("msg-cad-pessoa-cargo");
		}

		return null;
	}

	public String atualizar() {
		try {
			this.bo.save(this.pessoaCargo);
			msgs.addInfo().editadoComSucesso();
			pessoaCargo = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}

		return "/list/pessoaCargo?faces-redirect=true";
	}

	public String filtrar() {
		this.pessoaCargosLazy = this.bo.lazyList(this.pessoaCargoFilter);
		return null;
	}

	public String deletar() {
		this.bo.delete(this.pessoaCargo);
		this.pessoaCargo = null;
		return null;
	}

	public List<PessoaCargo> pessoaCargos() {
		return this.bo.findAll();
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

	public LazyDataModel<PessoaCargo> getPessoaCargosLazy() {
		if (this.pessoaCargosLazy == null) {
			this.pessoaCargosLazy = this.bo.lazyList(this.getPessoaCargoFilter());
		}

		return this.pessoaCargosLazy;
	}

	public void setPessoaCargosLazy(LazyDataModel<PessoaCargo> pessoaCargosLazy) {
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