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
import br.com.churchmanager.model.Perfil;
import br.com.churchmanager.model.filter.PerfilFilter;
import br.com.churchmanager.service.PerfilService;

@Named
@ViewScoped
public class PerfilMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Perfil perfil;
	private List<Perfil> perfils;
	private LazyDataModel<Perfil> perfilsLazy;
	private PerfilFilter perfilFilter;
	@Inject
	private PerfilService service;
	@Inject
	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		Perfil perfil = null;
		this.perfil = perfil;
	}

	public String salvar() {
		try {
			this.service.save(this.perfil);
			msgs.addInfo().cadastradoComSucesso();
			this.perfil = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());

		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.service.save(this.perfil);
			msgs.addInfo().editadoComSucesso();
			this.perfil = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
			return null;
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/perfil?faces-redirect=true";
	}

	public String filtrar() {
		this.perfilsLazy = this.service.lazyList(this.perfilFilter);
		return null;
	}

	public String deletar() {
		this.service.delete(this.perfil);
		this.perfil = null;
		return null;
	}

	private List<Perfil> perfis;

	public List<Perfil> perfis() {
		if (perfis == null && facesUtil.isNotPostback()) {
			perfis = this.service.findAll();
		}
		return perfis;
	}

	public Perfil getPerfil() {
		if (this.perfil == null) {
			this.perfil = new Perfil();
		}

		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfils() {
		if (this.perfils == null) {
			this.perfils = new ArrayList<>();
		}

		return this.perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	public LazyDataModel<Perfil> getPerfilsLazy() {
		if (this.perfilsLazy == null) {
			this.perfilsLazy = this.service.lazyList(this.perfilFilter);
		}

		return this.perfilsLazy;
	}

	public void setPerfilsLazy(LazyDataModel<Perfil> perfilsLazy) {
		this.perfilsLazy = perfilsLazy;
	}

	public PerfilFilter getPerfilFilter() {
		if (this.perfilFilter == null) {
			this.perfilFilter = new PerfilFilter();
		}

		return this.perfilFilter;
	}

	public void setPerfilFilter(PerfilFilter perfilFilter) {
		this.perfilFilter = perfilFilter;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

}