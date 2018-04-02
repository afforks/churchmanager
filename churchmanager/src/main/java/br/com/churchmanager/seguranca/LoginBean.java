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

import br.com.churchmanager.util.faces.FacesUtil;

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
	private String email;

	public void preRender() {
		if ("true".equals(this.request.getParameter("invalid"))) {
			FacesUtil.erro("msg", "Usuário não encontrado!", "Tente novamente.");
		}

	}

	public void login() throws ServletException, IOException {
		FacesUtil.informacao("mensagem", "Bem-vindo!", "");
		this.facesContext.getExternalContext().getFlash().setKeepMessages(true);
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