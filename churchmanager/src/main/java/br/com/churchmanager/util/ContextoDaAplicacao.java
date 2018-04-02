package br.com.churchmanager.util;

public class ContextoDaAplicacao {
	private static final String CONTEXTO_DA_APLICACAO = "";

	public static String cadastrar(String pagina) {
		return "/cad/" + pagina + ".xhtml";
	}

	public static String editar(String pagina) {
		return "/edit/" + pagina + ".xhtml";
	}

	public static String listar(String pagina) {
		return "/list/" + pagina + ".xhtml";
	}

	public static String login() {
		return "/login.xhtml";
	}

	public static String home() {
		return "/home.xhtml";
	}
}