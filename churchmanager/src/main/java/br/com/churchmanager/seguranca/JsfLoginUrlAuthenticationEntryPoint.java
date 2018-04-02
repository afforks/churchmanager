package br.com.churchmanager.seguranca;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.PortMapper;
import org.springframework.security.web.PortMapperImpl;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class JsfLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {
	private static final Log logger = LogFactory.getLog(JsfLoginUrlAuthenticationEntryPoint.class);
	private PortMapper portMapper = new PortMapperImpl();
	private PortResolver portResolver = new PortResolverImpl();
	private String loginFormUrl;
	private boolean forceHttps = false;
	private boolean useForward = false;
	protected RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void afterPropertiesSet() throws Exception {
		Assert.isTrue(StringUtils.hasText(this.loginFormUrl) && UrlUtils.isValidRedirectUrl(this.loginFormUrl),
				"loginFormUrl must be specified and must be a valid redirect URL");
		if (this.useForward && UrlUtils.isAbsoluteUrl(this.loginFormUrl)) {
			throw new IllegalArgumentException("useForward must be false if using an absolute loginFormURL");
		} else {
			Assert.notNull(this.portMapper, "portMapper must be specified");
			Assert.notNull(this.portResolver, "portResolver must be specified");
		}
	}

	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		return this.getLoginFormUrl();
	}

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String redirectUrl = null;
		if (this.useForward) {
			if (this.forceHttps && "http".equals(request.getScheme())) {
				redirectUrl = this.buildHttpsRedirectUrlForRequest(request);
			}

			if (redirectUrl == null) {
				String loginForm = this.determineUrlToUseForThisRequest(request, response, authException);
				if (logger.isDebugEnabled()) {
					logger.debug("Server side forward to: " + loginForm);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(loginForm);
				dispatcher.forward(request, response);
				return;
			}
		} else {
			redirectUrl = this.buildRedirectUrlToLoginPage(request, response, authException);
		}

		this.redirectStrategy.sendRedirect(request, response, redirectUrl);
	}

	protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {
		String loginForm = this.determineUrlToUseForThisRequest(request, response, authException);
		if (UrlUtils.isAbsoluteUrl(loginForm)) {
			return loginForm;
		} else {
			int serverPort = this.portResolver.getServerPort(request);
			String scheme = request.getScheme();
			RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
			urlBuilder.setScheme(scheme);
			urlBuilder.setServerName(request.getServerName());
			urlBuilder.setPort(serverPort);
			urlBuilder.setContextPath(request.getContextPath());
			urlBuilder.setPathInfo(loginForm);
			if (this.forceHttps && "http".equals(scheme)) {
				Integer httpsPort = this.portMapper.lookupHttpsPort(Integer.valueOf(serverPort));
				if (httpsPort != null) {
					urlBuilder.setScheme("https");
					urlBuilder.setPort(httpsPort.intValue());
				} else {
					logger.warn("Unable to redirect to HTTPS as no port mapping found for HTTP port " + serverPort);
				}
			}

			return urlBuilder.getUrl();
		}
	}

	protected String buildHttpsRedirectUrlForRequest(HttpServletRequest request) throws IOException, ServletException {
		int serverPort = this.portResolver.getServerPort(request);
		Integer httpsPort = this.portMapper.lookupHttpsPort(Integer.valueOf(serverPort));
		if (httpsPort != null) {
			RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
			urlBuilder.setScheme("https");
			urlBuilder.setServerName(request.getServerName());
			urlBuilder.setPort(httpsPort.intValue());
			urlBuilder.setContextPath(request.getContextPath());
			urlBuilder.setServletPath(request.getServletPath());
			urlBuilder.setPathInfo(request.getPathInfo());
			urlBuilder.setQuery(request.getQueryString());
			return urlBuilder.getUrl();
		} else {
			logger.warn("Unable to redirect to HTTPS as no port mapping found for HTTP port " + serverPort);
			return null;
		}
	}

	public void setForceHttps(boolean forceHttps) {
		this.forceHttps = forceHttps;
	}

	protected boolean isForceHttps() {
		return this.forceHttps;
	}

	public void setLoginFormUrl(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
	}

	public String getLoginFormUrl() {
		return this.loginFormUrl;
	}

	public void setPortMapper(PortMapper portMapper) {
		this.portMapper = portMapper;
	}

	protected PortMapper getPortMapper() {
		return this.portMapper;
	}

	public void setPortResolver(PortResolver portResolver) {
		this.portResolver = portResolver;
	}

	protected PortResolver getPortResolver() {
		return this.portResolver;
	}

	public void setUseForward(boolean useForward) {
		this.useForward = useForward;
	}

	protected boolean isUseForward() {
		return this.useForward;
	}

	public RedirectStrategy getRedirectStrategy() {
		return this.redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
}