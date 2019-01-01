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
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.model.filter.UsuarioFilter;
import br.com.churchmanager.service.UsuarioService;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<>();
	private LazyDataModel<Usuario> usuariosLazy;
	private UsuarioFilter usuarioFilter = new UsuarioFilter();
	private Boolean mudarSenha;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private FacesUtil facesUtil;
	@Inject
	private JsfMessage<Msgs> msgs;

	@PostConstruct
	public void init() {
		Usuario usuario = null;
		this.usuario = usuario == null ? new Usuario() : usuario;
	}

	public String salvar() {
		try {
			this.usuarioService.save(this.usuario);
			msgs.addInfo().cadastradoComSucesso();
			this.usuario = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
		}
		return null;
	}

	public String atualizar() {
		try {
			this.usuarioService.save(this.usuario);
			msgs.addInfo().editadoComSucesso();
			this.usuario = null;
		} catch (NegocioException | ViolacaoDeRestricaoException | DadosException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("msg");
			facesUtil.manterMensagem();
		}
		return "/list/usuario?faces-redirect=true";
	}

	public String filtrar() {
		this.usuariosLazy = this.usuarioService.lazyList(this.usuarioFilter);
		return null;
	}

	public void perfilSelecionado() {
		this.usuario.setPaginas(this.usuario.getPerfil().getPaginas());
	}

	public String deletar() {
		this.usuarioService.delete(this.usuario);
		this.usuario = null;
		return null;
	}

	public Usuario porEmail(String email) {
		return usuarioService.findByEmail(email);
	}

	// *************************************************************

	public LazyDataModel<Usuario> getUsuariosLazy() {
		if (this.usuariosLazy == null) {
			this.usuariosLazy = this.usuarioService.lazyList(this.usuarioFilter);
		}

		return this.usuariosLazy;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioFilter getUsuarioFilter() {
		return usuarioFilter;
	}

	public void setUsuarioFilter(UsuarioFilter usuarioFilter) {
		this.usuarioFilter = usuarioFilter;
	}

	public Boolean getMudarSenha() {
		return mudarSenha;
	}

	public void setMudarSenha(Boolean mudarSenha) {
		this.mudarSenha = mudarSenha;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsuariosLazy(LazyDataModel<Usuario> usuariosLazy) {
		this.usuariosLazy = usuariosLazy;
	}

}