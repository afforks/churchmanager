package br.com.churchmanager.seguranca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private String loginPageUrl;
	private boolean returnParameterEnabled;
	private String returnParameterName;

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException reason)
			throws IOException, ServletException {
		if (this.loginPageUrl != null && !"".equals(this.loginPageUrl)) {
			String redirectUrl = this.loginPageUrl;
			if (this.isReturnParameterEnabled()) {
				String redirectStrategy = this.getReturnParameterName();
				if (redirectStrategy == null || "".equals(redirectStrategy)) {
					throw new RuntimeException("redirectUrlReturnParameterName has not been defined");
				}

				redirectUrl = redirectUrl + "?" + redirectStrategy + "=" + request.getServletPath();
			}

			DefaultRedirectStrategy redirectStrategy1 = new DefaultRedirectStrategy();
			redirectStrategy1.sendRedirect(request, response, redirectUrl);
		} else {
			throw new RuntimeException("loginPageUrl has not been defined");
		}
	}

	public String getLoginPageUrl() {
		return this.loginPageUrl;
	}

	public void setLoginPageUrl(String loginPageUrl) {
		this.loginPageUrl = loginPageUrl;
	}

	public boolean isReturnParameterEnabled() {
		return this.returnParameterEnabled;
	}

	public void setReturnParameterEnabled(boolean returnParameterEnabled) {
		this.returnParameterEnabled = returnParameterEnabled;
	}

	public String getReturnParameterName() {
		return this.returnParameterName;
	}

	public void setReturnParameterName(String returnParameterName) {
		this.returnParameterName = returnParameterName;
	}
}