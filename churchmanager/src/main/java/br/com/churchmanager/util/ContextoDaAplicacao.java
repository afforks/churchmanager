package br.com.churchmanager.util;

import java.io.Serializable;

public class ContextoDaAplicacao implements Serializable {

	private static final long serialVersionUID = -5386328422266698124L;

	private static final String SERVER = "localhost";
	private static final String PORTA = "8080";
	private static final String PATH = "churchmanager";
	private static final String EXTENSAO = ".xhtml";
	public static final String CONTEXTO = SERVER + ":" + PORTA + "/" + PATH;

	public static String cadastrar(String pagina) {
		return CONTEXTO + "/cad/" + pagina + EXTENSAO;
	}

	public static String editar(String pagina, String id) {
		return CONTEXTO + "/edit/" + pagina + EXTENSAO + "?id=" + id;
	}

	public static String listar(String pagina) {
		return CONTEXTO + "/list/" + pagina + EXTENSAO;
	}

	public static String login() {
		return CONTEXTO + "/login" + EXTENSAO;
	}

	public static String home() {
		return CONTEXTO + "/home" + EXTENSAO;
	}

	public static String configuracao() {
		return CONTEXTO + "/configuracao" + EXTENSAO;
	}
}