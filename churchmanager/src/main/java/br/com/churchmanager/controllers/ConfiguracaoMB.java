package br.com.churchmanager.controllers;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.hibernate.validator.constraints.Email;

import br.com.churchmanager.exception.DadosException;
import br.com.churchmanager.exception.NegocioException;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.jsf.FacesUtil;
import br.com.churchmanager.jsf.Msgs;
import br.com.churchmanager.model.Configuracao;
import br.com.churchmanager.service.ConfiguaracaoService;

@Model
public class ConfiguracaoMB {

	@Inject
	private ConfiguaracaoService configuracaoService;

	@NotNull
	@Pattern(regexp = "^\\([1-9]{2}\\)[0-9]{4}\\-[0-9]{4}[0-9]{0,1}$|\\([1-9]{2}\\)[0-9]{5}\\-[0-9]{4}[0-9]{0,1}$")
	private String telefone;

	@NotNull
	@Email
	private String email;

	@Inject
	private FacesUtil facesUtil;

	@Inject
	private JsfMessage<Msgs> msgs;

	private Configuracao configuracao = new Configuracao();

	public void carregarUltimaConfiguracao() {
		this.configuracao = configuracaoService.buscarUltimaConfiguracao();
		facesUtil.atualizarComponente("group");
	}

	public String salvar() {
		try {
			configuracaoService.save(configuracao);
			msgs.addInfo().cadastradoComSucesso();
		} catch (ViolacaoDeRestricaoException | DadosException | NegocioException e) {
			msgs.addWarn().atencao("Atenção!", e.getMessage());
		} finally {
			facesUtil.atualizarComponente("config-msg");
		}
		return null;
	}

	public void resetarTelefone() {
		if (facesUtil.naoFalhouNaValidacao()) {
			msgs.addInfo().telefoneAdicionado();
			facesUtil.atualizarComponente("msg-tel-email");
			this.telefone = null;
		}
	}

	public void resetarEmail() {
		if (facesUtil.naoFalhouNaValidacao()) {
			msgs.addInfo().emailAdicionado();
			facesUtil.atualizarComponente("msg-tel-email");
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
