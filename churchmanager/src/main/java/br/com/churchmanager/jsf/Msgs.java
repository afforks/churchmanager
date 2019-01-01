package br.com.churchmanager.jsf;

import javax.inject.Named;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

@Named
@MessageBundle
public interface Msgs {

	@MessageTemplate("{cadastrado.sucesso}")
	String cadastradoComSucesso();

	@MessageTemplate("{editado.sucesso}")
	String editadoComSucesso();

	@MessageTemplate("{deletado.sucesso}")
	String deletadoComSucesso();

	@MessageTemplate("{status.alterado.sucesso}")
	String statusAlteradoSucesso();

	@MessageTemplate("{bemvindo.usuario.logado}")
	String bemVindo(String nomeCompleto);

	@MessageTemplate("{warn.message}")
	String atencao(String string, String message);

	@MessageTemplate("{negocio.exception}")
	String atencao(String message);

	@MessageTemplate("{telefone.adicionado}")
	String telefoneAdicionado();

	@MessageTemplate("{email.adicionado}")
	String emailAdicionado();

	@MessageTemplate("{error.message}")
	String error(String string, String string2);

	@MessageTemplate("{info.message}")
	String info(String string, String string2);

}
