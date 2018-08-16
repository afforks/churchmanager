package br.com.churchmanager.util.faces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class FacesUtil {

	private FacesUtil() {
	}

	public static boolean falhouNaValidacao() {
		return FacesContext.getCurrentInstance().isValidationFailed();
	}

	public static boolean naoFalhouNaValidacao() {
		return !falhouNaValidacao();
	}

	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	public static boolean isNotPostback() {
		return !isPostback();
	}

	public static void informacao(String id, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail));
	}

	public static void atencao(String id, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_WARN, message, detail));
	}

	public static void erro(String id, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(id,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail));
	}

	public static void fatal(String id, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(id,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, message, detail));
	}

	public static void atualizaComponente(String id) {
		PrimeFaces.current().ajax().update(id);
	}

	public static void executarJS(String statement) {
		PrimeFaces.current().executeScript(statement);
	}

	public static void manterMensagem() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

}
