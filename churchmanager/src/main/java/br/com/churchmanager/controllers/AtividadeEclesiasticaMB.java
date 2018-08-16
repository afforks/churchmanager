package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.AtividadeEclesiasticaBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.AtividadeEclesiastica;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.AtividadeEclesiasticaFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class AtividadeEclesiasticaMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private AtividadeEclesiastica atividade;
	private List<AtividadeEclesiastica> atividades;
	private MyLazyDataModel<AtividadeEclesiastica> atividadesLazy;
	private AtividadeEclesiasticaFilter atividadeFilter;
	@Inject
	private AtividadeEclesiasticaBO bo;

	@PostConstruct
	public void init() {
		AtividadeEclesiastica atividade = BuscaObjeto.comParametroGET("id", this.bo);
		this.atividade = atividade;
	}

	public String salvar() {
		try {
			this.bo.salvar(this.atividade);
			FacesUtil.informacao("msg", "Cadastrado com sucesso!", this.atividade.toString());
			this.atividade = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", "O nome '"+atividade.getNome()+"' está duplicado, por favor, informe outro!");
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
			this.bo.atualizar(this.atividade);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.atividade.toString());
			this.atividade = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", "O nome '"+atividade.getNome()+"' está duplicado, por favor, informe outro!");
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
		return "/list/atividade?faces-redirect=true";
	}

	public String filtrar() {
		this.atividadesLazy = this.bo.filtrar(this.atividadeFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.atividade);
		this.atividade = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	public List<AtividadeEclesiastica> atividades() {
		return this.bo.listar();
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

	public MyLazyDataModel<AtividadeEclesiastica> getAtividadeEclesiasticasLazy() {
		if (this.atividadesLazy == null) {
			this.atividadesLazy = this.bo.filtrar(this.atividadeFilter);
		}

		return this.atividadesLazy;
	}

	public void setAtividadeEclesiasticasLazy(MyLazyDataModel<AtividadeEclesiastica> atividadesLazy) {
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