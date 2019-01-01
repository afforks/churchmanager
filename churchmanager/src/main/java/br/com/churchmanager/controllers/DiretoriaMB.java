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
import br.com.churchmanager.model.Diretoria;
import br.com.churchmanager.model.Pessoa;
import br.com.churchmanager.model.PessoaCargo;
import br.com.churchmanager.model.filter.DiretoriaFilter;
import br.com.churchmanager.service.DiretoriaService;

@Named
@ViewScoped
public class DiretoriaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Diretoria diretoria;
	private List<Diretoria> diretorias;
	private LazyDataModel<Diretoria> diretoriasLazy;
	private DiretoriaFilter diretoriaFilter;
	private Pessoa pessoa;
	private Cargo cargo;
	@Inject
	private DiretoriaService diretoriaService;

	@Inject
	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		Diretoria diretoria = null;
		this.diretoria = diretoria;
	}

	public String salvar() {
		try {
			this.diretoriaService.save(this.diretoria);
			msgs.addInfo().cadastradoComSucesso();
			this.diretoria = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.diretoriaService.save(this.diretoria);
			msgs.addInfo().editadoComSucesso();
			this.diretoria = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/diretoria?faces-redirect=true";
	}

	public String filtrar() {
		this.diretoriasLazy = this.diretoriaService.lazyList(this.diretoriaFilter);
		return null;
	}

	public String deletar() {
		this.diretoriaService.delete(this.diretoria);
		this.diretoria = null;
		return null;
	}

	public List<Diretoria> diretorias() {
		return this.diretoriaService.findAll();
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

	public LazyDataModel<Diretoria> getDiretoriasLazy() {
		if (this.diretoriasLazy == null) {
			this.diretoriasLazy = this.diretoriaService.lazyList(this.diretoriaFilter);
		}

		return this.diretoriasLazy;
	}

	public void setDiretoriasLazy(LazyDataModel<Diretoria> diretoriasLazy) {
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

	public boolean naoContemPessoaCargo(PessoaCargo pc) {
		return !this.getDiretoria().getPessoaCargos().contains(pc);
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