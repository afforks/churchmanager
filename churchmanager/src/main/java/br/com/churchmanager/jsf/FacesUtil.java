package br.com.churchmanager.jsf;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

public class FacesUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext context;

	@Inject
	private PrimeFaces primefaces;

	@Inject
	private ExternalContext external;

	public FacesContext facesContext() {
		return context;
	}

	public ExternalContext externalContext() {
		return external;
	}

	public boolean isPostback() {
		return context.isPostback();
	}

	public boolean isNotPostback() {
		return !isPostback();
	}

	public boolean falhouNaValidacao() {
		return context.isValidationFailed();
	}

	public void atualizarComponente(String id) {
		primefaces.ajax().update(id);
	}

	public void updateComponentes(String... ids) {
		primefaces.ajax().update(ids);
	}

	public void executarJavascript(String js) {
		primefaces.executeScript(js);
	}

	public void resetarInput(String id) {
		primefaces.resetInputs(id);
	}

	public void resetarInputs(String ids) {
		primefaces.resetInputs(ids);
	}

	public void scrollTo(String id) {
		primefaces.scrollTo(id);
	}

	public boolean naoFalhouNaValidacao() {
		return !falhouNaValidacao();
	}

	public void manterMensagem() {
		external.getFlash().setKeepMessages(true);
	}

}
