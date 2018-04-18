package br.com.churchmanager.util;

import javax.faces.context.FacesContext;

public class ContextoDaAplicacao {

	public static final String EXTENSAO = ".xhtml";
	public static final String HOST = "";
	public static final String CONTEXTO = HOST + "";

	public static String cadastrar(String pagina) {
		return CONTEXTO + "/cad/" + pagina + EXTENSAO;
	}

	public static String editar(String pagina) {
		return CONTEXTO + "/edit/" + pagina + EXTENSAO;
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
}