package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.PerfilBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Perfil;
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.filter.PerfilFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class PerfilMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Perfil perfil;
	private List<Perfil> perfils;
	private MyLazyDataModel<Perfil> perfilsLazy;
	private PerfilFilter perfilFilter;
	@Inject
	private PerfilBO bo;

	@PostConstruct
	public void init() {
		Perfil perfil = (Perfil) BuscaObjeto.comParametroGET(Perfil.class, "id", this.bo);
		this.perfil = perfil;
	}

	public String salvar() {
		try {
			this.bo.salvar(this.perfil);
			FacesUtil.informacao("msg", "Cadastrado com sucesso!", this.perfil.toString());
			FacesUtil.atualizaComponente("msg");
			this.perfil = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!",
					"O nome '" + perfil.getNome() + "' está duplicado, por favor, informe outro!");
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
			this.bo.atualizar(this.perfil);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.perfil.toString());
			this.perfil = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!",
					"O nome '" + perfil.getNome() + "' está duplicado, por favor, informe outro!");
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
		return "/list/perfil?faces-redirect=true";
	}

	public String filtrar() {
		this.perfilsLazy = this.bo.filtrar(this.perfilFilter);
		return null;
	}

	public String deletar() {
		this.bo.deletar(this.perfil);
		this.perfil = null;
		return null;
	}

	public List<Perfil> perfis() {
		return this.bo.listar();
	}

	public Status[] listarStatus() {
		return Status.values();
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

	public MyLazyDataModel<Perfil> getPerfilsLazy() {
		if (this.perfilsLazy == null) {
			this.perfilsLazy = this.bo.filtrar(this.perfilFilter);
		}

		return this.perfilsLazy;
	}

	public void setPerfilsLazy(MyLazyDataModel<Perfil> perfilsLazy) {
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
}