package br.com.churchmanager.seguranca;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.churchmanager.seguranca.UsuarioLogado;
import br.com.churchmanager.seguranca.UsuarioSistema;

@Named
@RequestScoped
public class Seguranca {
	@Inject
	private ExternalContext externalContext;

	public String getNomeUsuario() {
		String nome = null;
		UsuarioSistema usuarioLogado = this.getUsuarioLogado();
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNomeCompleto();
		}

		return nome;
	}

	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario;
	}

	public boolean isEmitirPedidoPermitido() {
		return this.externalContext.isUserInRole("ADMINISTRADORES");
	}

	public boolean isCadastrarPagina() {
		return this.externalContext.isUserInRole("CADASTRAR_PAGINA");
	}

	public boolean isEditarPagina() {
		return this.externalContext.isUserInRole("EDITAR_PAGINA");
	}

	public boolean isListarPagina() {
		return this.externalContext.isUserInRole("LISTAR_PAGINA");
	}

	public boolean isCadastrarUsuario() {
		return this.externalContext.isUserInRole("CADASTRAR_USUARIO");
	}

	public boolean isEditarUsuario() {
		return this.externalContext.isUserInRole("EDITAR_USUARIO");
	}

	public boolean isListarUsuario() {
		return this.externalContext.isUserInRole("LISTAR_USUARIO");
	}

	public boolean isCadastrarPerfil() {
		return this.externalContext.isUserInRole("CADASTRAR_PERFIL");
	}

	public boolean isEditarPerfil() {
		return this.externalContext.isUserInRole("EDITAR_PERFIL");
	}

	public boolean isListarPerfil() {
		return this.externalContext.isUserInRole("LISTAR_PERFIL");
	}

	public boolean isCadastrarPessoa() {
		return this.externalContext.isUserInRole("CADASTRAR_PESSOA");
	}

	public boolean isEditarPessoa() {
		return this.externalContext.isUserInRole("EDITAR_PESSOA");
	}

	public boolean isListarPessoa() {
		return this.externalContext.isUserInRole("LISTAR_PESSOA");
	}

	public boolean isCadastrarAtividadeEclesiastica() {
		return this.externalContext.isUserInRole("CADASTRAR_ATIVIDADE_ECLESIASTICA");
	}

	public boolean isEditarAtividadeEclesiastica() {
		return this.externalContext.isUserInRole("EDITAR_ATIVIDADE_ECLESIASTICA");
	}

	public boolean isListarAtividadeEclesiastica() {
		return this.externalContext.isUserInRole("LISTAR_ATIVIDADE_ECLESIASTICA");
	}

	public boolean isCadastrarCategoriaMovimentacao() {
		return this.externalContext.isUserInRole("CADASTRAR_CATEGORIA_MOVIMENTACAO");
	}

	public boolean isEditarCategoriaMovimentacao() {
		return this.externalContext.isUserInRole("EDITAR_CATEGORIA_MOVIMENTACAO");
	}

	public boolean isListarCategoriaMovimentacao() {
		return this.externalContext.isUserInRole("LISTAR_CATEGORIA_MOVIMENTACAO");
	}

	public boolean isCadastrarMovimentacao() {
		return this.externalContext.isUserInRole("CADASTRAR_MOVIMENTACAO");
	}

	public boolean isEditarMovimentacao() {
		return this.externalContext.isUserInRole("EDITAR_MOVIMENTACAO");
	}

	public boolean isListarMovimentacao() {
		return this.externalContext.isUserInRole("LISTAR_MOVIMENTACAO");
	}

	public boolean isCadastrarEvento() {
		return this.externalContext.isUserInRole("CADASTRAR_EVENTO");
	}

	public boolean isEditarEvento() {
		return this.externalContext.isUserInRole("EDITAR_EVENTO");
	}

	public boolean isListarEvento() {
		return this.externalContext.isUserInRole("LISTAR_EVENTO");
	}

	public boolean isCadastrarPatrimonio() {
		return this.externalContext.isUserInRole("CADASTRAR_PATRIMONIO");
	}

	public boolean isEditarPatrimonio() {
		return this.externalContext.isUserInRole("EDITAR_PATRIMONIO");
	}

	public boolean isListarPatrimonio() {
		return this.externalContext.isUserInRole("LISTAR_PATRIMONIO");
	}

	public boolean isCadastrarDizimo() {
		return this.externalContext.isUserInRole("CADASTRAR_DIZIMO");
	}

	public boolean isEditarDizimo() {
		return this.externalContext.isUserInRole("EDITAR_DIZIMO");
	}

	public boolean isListarDizimo() {
		return this.externalContext.isUserInRole("LISTAR_DIZIMO");
	}

	public boolean isCadastrarCargo() {
		return this.externalContext.isUserInRole("CADASTRAR_CARGO");
	}

	public boolean isEditarCargo() {
		return this.externalContext.isUserInRole("EDITAR_CARGO");
	}

	public boolean isListarCargo() {
		return this.externalContext.isUserInRole("LISTAR_CARGO");
	}

	public boolean isCadastrarDiretoria() {
		return this.externalContext.isUserInRole("CADASTRAR_DIRETORIA");
	}

	public boolean isEditarDiretoria() {
		return this.externalContext.isUserInRole("EDITAR_DIRETORIA");
	}

	public boolean isListarDiretoria() {
		return this.externalContext.isUserInRole("LISTAR_DIRETORIA");
	}
}