package br.com.churchmanager.util;

public class ContextoDaAplicacao {

	public static final String EXTENSAO = ".xhtml";

	public static String cadastrar(String pagina) {
		return "/cad/" + pagina + EXTENSAO;
	}

	public static String editar(String pagina) {
		return "/edit/" + pagina + EXTENSAO;
	}

	public static String listar(String pagina) {
		return "/list/" + pagina + EXTENSAO;
	}

	public static String login() {
		return "/login.xhtml";
	}

	public static String home() {
		return "/home.xhtml";
	}
}