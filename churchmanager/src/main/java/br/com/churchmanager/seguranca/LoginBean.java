package br.com.churchmanager.seguranca;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.churchmanager.jsf.FacesUtil;
import br.com.churchmanager.jsf.Msgs;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletRequest request;

	@Inject
	private HttpServletResponse response;

	@Inject
	private JsfMessage<Msgs> msgs;

	@Inject
	private FacesUtil facesUtil;

	@NotNull
	@NotEmpty
	@NotBlank
	@Email
	private String email;

	public void preRender() {
		if ("true".equals(this.request.getParameter("invalid"))) {
			msgs.addError().error("Usuário não encontrado!", "Tente novamente.");
			facesUtil.atualizarComponente("msgs");
		}
	}

	public void login() throws ServletException, IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/login.xhtml");
		dispatcher.forward(this.request, this.response);
		this.facesContext.responseComplete();
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}