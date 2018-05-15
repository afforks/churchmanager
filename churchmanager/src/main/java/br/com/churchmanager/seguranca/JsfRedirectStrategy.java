package br.com.churchmanager.seguranca;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;

public class JsfRedirectStrategy implements RedirectStrategy {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private boolean contextRelative;

	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		String redirectUrl = this.calculateRedirectUrl(request.getContextPath(), url);
		redirectUrl = response.encodeRedirectURL(redirectUrl);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Redirecting to \'" + redirectUrl + "\'");
		}

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
			return this.contextRelative ? url : contextPath + url;
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
}