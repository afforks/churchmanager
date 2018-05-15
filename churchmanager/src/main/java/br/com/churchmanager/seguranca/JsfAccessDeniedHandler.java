package br.com.churchmanager.seguranca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.UrlUtils;

public class JsfAccessDeniedHandler implements AccessDeniedHandler {
	private String loginPath;
	private boolean contextRelative;

	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String redirectUrl = this.calculateRedirectUrl(request.getContextPath(), this.loginPath);
		redirectUrl = response.encodeRedirectURL(redirectUrl);
		boolean ajaxRedirect = request.getHeader("faces-request") != null
				&& request.getHeader("faces-request").toLowerCase().indexOf("ajax") > -1;
		if (ajaxRedirect) {
			String ajaxRedirectXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\""
					+ redirectUrl + "\"></redirect></partial-response>";
			response.setContentType("text/xml");
			response.getWriter().write(ajaxRedirectXml);
		} else {
			response.sendRedirect(redirectUrl);
		}

	}

	private String calculateRedirectUrl(String contextPath, String url) {
		if (!UrlUtils.isAbsoluteUrl(url)) {
			return !this.contextRelative ? url : contextPath + url;
		} else if (!this.contextRelative) {
			return url;
		} else {
			url = url.substring(url.indexOf("://") + 3);
			url = url.substring(url.indexOf(contextPath) + contextPath.length());
			if (url.length() > 1 && url.charAt(0) == 47) {
				url = url.substring(1);
			}

			return url;
		}
	}

	public void setContextRelative(boolean useRelativeContext) {
		this.contextRelative = useRelativeContext;
	}

	public String getLoginPath() {
		return this.loginPath;
	}

	public void setLoginPath(String loginPath) {
		this.loginPath = loginPath;
	}
}