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
import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.filter.AtividadeEclesiasticaFilter;
import br.com.churchmanager.service.AtividadeEclesiasticaService;

@Named
@ViewScoped
public class AtividadeEclesiasticaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private AtividadeEclesiastica atividade;
	private List<AtividadeEclesiastica> atividades;
	private LazyDataModel<AtividadeEclesiastica> atividadesLazy;
	private AtividadeEclesiasticaFilter atividadeFilter;

	@Inject
	private AtividadeEclesiasticaService atividadeService;

	@Inject
	private JsfMessage<Msgs> message;

	@Inject
	private FacesUtil facesUtil;

	@PostConstruct
	public void init() {
		AtividadeEclesiastica atividade = null;
		this.atividade = atividade;
	}

	public String salvar() {
		try {
			this.atividadeService.save(this.atividade);
			message.addInfo().cadastradoComSucesso();
			this.atividade = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			message.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.atividadeService.save(this.atividade);
			message.addInfo().cadastradoComSucesso();
			this.atividade = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			message.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/atividade?faces-redirect=true";
	}

	public String filtrar() {
		this.atividadesLazy = this.atividadeService.lazyList(this.atividadeFilter);
		return null;
	}

	public String deletar() {
		this.atividadeService.delete(this.atividade);
		this.atividade = null;
		return null;
	}

	public List<AtividadeEclesiastica> atividades() {
		return this.atividadeService.findAll();
	}

	public AtividadeEclesiastica getAtividadeEclesiastica() {
		if (this.atividade == null) {
			this.atividade = new AtividadeEclesiastica();
		}

		return this.atividade;
	}

	public void setAtividadeEclesiastica(AtividadeEclesiastica atividade) {
		this.atividade = atividade;
	}

	public List<AtividadeEclesiastica> getAtividadeEclesiasticas() {
		if (this.atividades == null) {
			this.atividades = new ArrayList<>();
		}

		return this.atividades;
	}

	public void setAtividadeEclesiasticas(List<AtividadeEclesiastica> atividades) {
		this.atividades = atividades;
	}

	public LazyDataModel<AtividadeEclesiastica> getAtividadeEclesiasticasLazy() {
		if (this.atividadesLazy == null) {
			this.atividadesLazy = this.atividadeService.lazyList(this.atividadeFilter);
		}

		return this.atividadesLazy;
	}

	public void setAtividadeEclesiasticasLazy(LazyDataModel<AtividadeEclesiastica> atividadesLazy) {
		this.atividadesLazy = atividadesLazy;
	}

	public AtividadeEclesiasticaFilter getAtividadeEclesiasticaFilter() {
		if (this.atividadeFilter == null) {
			this.atividadeFilter = new AtividadeEclesiasticaFilter();
		}

		return this.atividadeFilter;
	}

	public void setAtividadeEclesiasticaFilter(AtividadeEclesiasticaFilter atividadeFilter) {
		this.atividadeFilter = atividadeFilter;
	}
}