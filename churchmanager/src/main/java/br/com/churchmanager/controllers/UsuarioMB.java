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
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.model.filter.UsuarioFilter;
import br.com.churchmanager.util.BuscaObjeto;
import br.com.churchmanager.util.MyLazyDataModel;
import br.com.churchmanager.util.faces.FacesUtil;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private List<Usuario> usuarios;
	private MyLazyDataModel<Usuario> usuariosLazy;
	private UsuarioFilter usuarioFilter;
	private Boolean mudarSenha;
	@Inject
	private UsuarioBO bo;

	@PostConstruct
	public void init() {
		Usuario usuario = (Usuario) BuscaObjeto.comParametroGET(Usuario.class, "id", this.bo);
		this.usuario = usuario;
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

	public Status[] listarStatus() {
		return Status.values();
	}

	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}

		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			this.usuarios = new ArrayList<>();
		}

		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public MyLazyDataModel<Usuario> getUsuariosLazy() {
		if (this.usuariosLazy == null) {
			this.usuariosLazy = this.bo.filtrar(this.usuarioFilter);
		}

		return this.usuariosLazy;
	}

	public void setUsuariosLazy(MyLazyDataModel<Usuario> usuariosLazy) {
		this.usuariosLazy = usuariosLazy;
	}

	public UsuarioFilter getUsuarioFilter() {
		if (this.usuarioFilter == null) {
			this.usuarioFilter = new UsuarioFilter();
		}

		return this.usuarioFilter;
	}

	public void setUsuarioFilter(UsuarioFilter usuarioFilter) {
		this.usuarioFilter = usuarioFilter;
	}

	public Boolean getMudarSenha() {
		return this.mudarSenha;
	}

	public void setMudarSenha(Boolean mudarSenha) {
		this.mudarSenha = mudarSenha;
	}
}