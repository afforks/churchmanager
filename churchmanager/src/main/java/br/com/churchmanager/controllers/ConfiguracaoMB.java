package br.com.churchmanager.controllers;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import br.com.churchmanager.bo.ConfiguracaoBO;
import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Configuracao;
import br.com.churchmanager.util.faces.FacesUtil;

@Model
public class ConfiguracaoMB {

	@Inject
	private ConfiguracaoBO configuracaoBO;

	@NotNull
	@Pattern(regexp = "^\\([1-9]{2}\\)[0-9]{4}\\-[0-9]{4}[0-9]{0,1}$|\\([1-9]{2}\\)[0-9]{5}\\-[0-9]{4}[0-9]{0,1}$")
	private String telefone;

	@NotNull
	@Email
	private String email;

	private Configuracao configuracao = new Configuracao();

	public void carregarUltimaConfiguracao() {
		this.configuracao = configuracaoBO.buscarUltimaConfiguracao();
		FacesUtil.atualizaComponente("group");
	}

	public String salvar() {
		try {
			configuracaoBO.salvar(configuracao);
			FacesUtil.informacao("config-msg", "Cadastrado com sucesso!", null);
		} catch (ViolacaoDeRestricaoException | DadosException | NegocioException e) {

			FacesUtil.erro("config-msg", "Atenção!", e.getMessage());
		} finally {
			FacesUtil.atualizaComponente("config-msg");
		}
		return null;
	}

	public void resetarTelefone() {
		if (FacesUtil.naoFalhouNaValidacao()) {
			FacesUtil.informacao("msg-tel-email", "Telefone adicionado!", "Número: " + this.telefone);
			FacesUtil.atualizaComponente("msg-tel-email");
			this.telefone = null;
		}
	}

	public void resetarEmail() {
		if (FacesUtil.naoFalhouNaValidacao()) {
			FacesUtil.informacao("msg-tel-email", "E-mail adicionado!", "E-mail: " + this.email);
			FacesUtil.atualizaComponente("msg-tel-email");
			this.email = null;
		}
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Configuracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}

}
