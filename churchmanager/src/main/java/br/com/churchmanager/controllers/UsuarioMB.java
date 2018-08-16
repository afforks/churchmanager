package br.com.churchmanager.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churchmanager.bo.UsuarioBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.model.filter.UsuarioFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class UsuarioMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<>();
	private MyLazyDataModel<Usuario> usuariosLazy;
	private UsuarioFilter usuarioFilter = new UsuarioFilter();
	private Boolean mudarSenha;
	
	@Inject
	private UsuarioBO bo;

	@PostConstruct
	public void init() {
		Usuario usuario = BuscaObjeto.comParametroGET("id", this.bo);
		this.usuario = usuario == null ? new Usuario() : usuario;
	}

	public String salvar() {
		try {
			this.bo.salvar(this.usuario);
			FacesUtil.informacao("msg", "Cadastrado com sucesso!", this.usuario.toString());
			this.usuario = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", "O e-mail '"+usuario.getEmail()+"' está duplicado, por favor, informe outro!");
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
			this.bo.atualizar(this.usuario);
			FacesUtil.informacao("msg", "Editado com sucesso!", this.usuario.toString());
			this.usuario = null;
		} catch (NegocioException e) {
			FacesUtil.atencao("msg", "Atenção!", e.getMessage());
			e.printStackTrace();
			return null;
		} catch (ViolacaoDeRestricaoException e) {
			FacesUtil.atencao("msg", "Atenção!", "O e-mail '"+usuario.getEmail()+"' está duplicado, por favor, informe outro!");
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
		return "/list/usuario?faces-redirect=true";
	}

	public String filtrar() {
		this.usuariosLazy = this.bo.filtrar(this.usuarioFilter);
		return null;
	}

	public void perfilSelecionado() {
		this.usuario.setPaginas(this.usuario.getPerfil().getPaginas());
	}

	public String deletar() {
		this.bo.deletar(this.usuario);
		this.usuario = null;
		return null;
	}

	public Usuario porEmail(String email) {
		return bo.porEmail(email);
	}
	
	//*************************************************************
	
	public MyLazyDataModel<Usuario> getUsuariosLazy() {
		if (this.usuariosLazy == null) {
			this.usuariosLazy = this.bo.filtrar(this.usuarioFilter);
		}

		return this.usuariosLazy;
	}

	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
}