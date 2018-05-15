package br.com.churchmanager.seguranca;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import br.com.churchmanager.seguranca.SecurityConfig;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	public SecurityWebApplicationInitializer() {
		super(new Class[]{SecurityConfig.class});
	}
}