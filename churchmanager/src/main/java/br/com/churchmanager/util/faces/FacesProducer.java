package br.com.churchmanager.util.faces;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

@ApplicationScoped
public class FacesProducer {

	@Produces
	@RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@Produces
	@RequestScoped
	public ExternalContext getExternalContext() {
		return this.getFacesContext().getExternalContext();
	}

	@Produces
	@RequestScoped
	public HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse) this.getExternalContext().getResponse();
	}
}