package br.com.churchmanager.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.churchmanager.seguranca.AppUserDetailsService;
import br.com.churchmanager.seguranca.CustomAuthenticationEntryPoint;
import br.com.churchmanager.seguranca.JsfAccessDeniedHandler;
import br.com.churchmanager.seguranca.JsfLoginUrlAuthenticationEntryPoint;
import br.com.churchmanager.seguranca.JsfRedirectStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public AppUserDetailsService userDetailService() {
		return new AppUserDetailsService();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailService()).passwordEncoder(new Md5PasswordEncoder());
	}

	protected void configure(HttpSecurity http) throws Exception {
		JsfLoginUrlAuthenticationEntryPoint jsfLogin = new JsfLoginUrlAuthenticationEntryPoint();
		jsfLogin.setLoginFormUrl("/login.xhtml");
		jsfLogin.setRedirectStrategy(new JsfRedirectStrategy());
		JsfAccessDeniedHandler jsfAccessDenied = new JsfAccessDeniedHandler();
		jsfAccessDenied.setLoginPath("/acesso-negado.xhtml");
		jsfAccessDenied.setContextRelative(true);
		CustomAuthenticationEntryPoint custom = new CustomAuthenticationEntryPoint();
		custom.setLoginPageUrl("/login.xhtml");
		custom.setReturnParameterEnabled(true);
		custom.setReturnParameterName("page");
		((HttpSecurity) ((HttpSecurity) ((FormLoginConfigurer) ((FormLoginConfigurer) ((HttpSecurity) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) ((HttpSecurity) ((HttpSecurity) http
				.csrf().disable()).headers().frameOptions().sameOrigin().and()).authorizeRequests().antMatchers(
						new String[]{"/form/**", "/WEB-INF/template/**"})).denyAll().antMatchers(new String[]{
								"/login.xhtml", "/erro.xhtml", "/javax.faces.resource/**", "/nao-encontrado.xhtml"}))
										.permitAll().antMatchers(new String[]{"/home.xhtml", "/acesso-negado.xhtml"}))
												.authenticated().antMatchers(new String[]{"/cad/pagina.xhtml"}))
														.hasAnyRole(new String[]{"CADASTRAR_PAGINA"})
														.antMatchers(new String[]{"/edit/pagina.xhtml"}))
																.hasAnyRole(new String[]{"EDITAR_PAGINA"})
																.antMatchers(new String[]{"/list/pagina.xhtml"}))
																		.hasAnyRole(new String[]{"LISTAR_PAGINA"})
																		.antMatchers(new String[]{"/cad/perfil.xhtml"}))
																				.hasAnyRole(new String[]{
																						"CADASTRAR_PERFIL"})
																				.antMatchers(new String[]{
																						"/edit/perfil.xhtml"}))
																								.hasAnyRole(
																										new String[]{
																												"EDITAR_PERFIL"})
																								.antMatchers(
																										new String[]{
																												"/list/perfil.xhtml"}))
																														.hasAnyRole(
																																new String[]{
																																		"LISTAR_PERFIL"})
																														.antMatchers(
																																new String[]{
																																		"/cad/usuario.xhtml"}))
																																				.hasAnyRole(
																																						new String[]{
																																								"CADASTRAR_USUARIO"})
																																				.antMatchers(
																																						new String[]{
																																								"/edit/usuario.xhtml"}))
																																										.hasAnyRole(
																																												new String[]{
																																														"EDITAR_USUARIO"})
																																										.antMatchers(
																																												new String[]{
																																														"/list/usuario.xhtml"}))
																																																.hasAnyRole(
																																																		new String[]{
																																																				"LISTAR_USUARIO"})
																																																.antMatchers(
																																																		new String[]{
																																																				"/cad/pessoa.xhtml"}))
																																																						.hasAnyRole(
																																																								new String[]{
																																																										"CADASTRAR_PESSOA"})
																																																						.antMatchers(
																																																								new String[]{
																																																										"/edit/pessoa.xhtml"}))
																																																												.hasAnyRole(
																																																														new String[]{
																																																																"EDITAR_PESSOA"})
																																																												.antMatchers(
																																																														new String[]{
																																																																"/list/pessoa.xhtml"}))
																																																																		.hasAnyRole(
																																																																				new String[]{
																																																																						"LISTAR_PESSOA"})
																																																																		.antMatchers(
																																																																				new String[]{
																																																																						"/cad/atividade.xhtml"}))
																																																																								.hasAnyRole(
																																																																										new String[]{
																																																																												"CADASTRAR_ATIVIDADE_ECLESIASTICA"})
																																																																								.antMatchers(
																																																																										new String[]{
																																																																												"/edit/atividade.xhtml"}))
																																																																														.hasAnyRole(
																																																																																new String[]{
																																																																																		"EDITAR_ATIVIDADE_ECLESIASTICA"})
																																																																														.antMatchers(
																																																																																new String[]{
																																																																																		"/list/atividade.xhtml"}))
																																																																																				.hasAnyRole(
																																																																																						new String[]{
																																																																																								"LISTAR_ATIVIDADE_ECLESIASTICA"})
																																																																																				.antMatchers(
																																																																																						new String[]{
																																																																																								"/cad/categoria.xhtml"}))
																																																																																										.hasAnyRole(
																																																																																												new String[]{
																																																																																														"CADASTRAR_CATEGORIA_MOVIMENTACAO"})
																																																																																										.antMatchers(
																																																																																												new String[]{
																																																																																														"/edit/categoria.xhtml"}))
																																																																																																.hasAnyRole(
																																																																																																		new String[]{
																																																																																																				"EDITAR_CATEGORIA_MOVIMENTACAO"})
																																																																																																.antMatchers(
																																																																																																		new String[]{
																																																																																																				"/list/categoria.xhtml"}))
																																																																																																						.hasAnyRole(
																																																																																																								new String[]{
																																																																																																										"LISTAR_CATEGORIA_MOVIMENTACAO"})
																																																																																																						.antMatchers(
																																																																																																								new String[]{
																																																																																																										"/cad/movimentacao.xhtml"}))
																																																																																																												.hasAnyRole(
																																																																																																														new String[]{
																																																																																																																"CADASTRAR_MOVIMENTACAO"})
																																																																																																												.antMatchers(
																																																																																																														new String[]{
																																																																																																																"/edit/movimentacao.xhtml"}))
																																																																																																																		.hasAnyRole(
																																																																																																																				new String[]{
																																																																																																																						"EDITAR_MOVIMENTACAO"})
																																																																																																																		.antMatchers(
																																																																																																																				new String[]{
																																																																																																																						"/list/movimentacao.xhtml"}))
																																																																																																																								.hasAnyRole(
																																																																																																																										new String[]{
																																																																																																																												"LISTAR_MOVIMENTACAO"})
																																																																																																																								.antMatchers(
																																																																																																																										new String[]{
																																																																																																																												"/cad/evento.xhtml"}))
																																																																																																																														.hasAnyRole(
																																																																																																																																new String[]{
																																																																																																																																		"CADASTRAR_EVENTO"})
																																																																																																																														.antMatchers(
																																																																																																																																new String[]{
																																																																																																																																		"/edit/evento.xhtml"}))
																																																																																																																																				.hasAnyRole(
																																																																																																																																						new String[]{
																																																																																																																																								"EDITAR_EVENTO"})
																																																																																																																																				.antMatchers(
																																																																																																																																						new String[]{
																																																																																																																																								"/list/evento.xhtml"}))
																																																																																																																																										.hasAnyRole(
																																																																																																																																												new String[]{
																																																																																																																																														"LISTAR_EVENTO"})
																																																																																																																																										.antMatchers(
																																																																																																																																												new String[]{
																																																																																																																																														"/cad/patrimonio.xhtml"}))
																																																																																																																																																.hasAnyRole(
																																																																																																																																																		new String[]{
																																																																																																																																																				"CADASTRAR_PATRIMONIO"})
																																																																																																																																																.antMatchers(
																																																																																																																																																		new String[]{
																																																																																																																																																				"/edit/patrimonio.xhtml"}))
																																																																																																																																																						.hasAnyRole(
																																																																																																																																																								new String[]{
																																																																																																																																																										"EDITAR_PATRIMONIO"})
																																																																																																																																																						.antMatchers(
																																																																																																																																																								new String[]{
																																																																																																																																																										"/list/patrimonio.xhtml"}))
																																																																																																																																																												.hasAnyRole(
																																																																																																																																																														new String[]{
																																																																																																																																																																"LISTAR_PATRIMONIO"})
																																																																																																																																																												.antMatchers(
																																																																																																																																																														new String[]{
																																																																																																																																																																"/cad/dizimo.xhtml"}))
																																																																																																																																																																		.hasAnyRole(
																																																																																																																																																																				new String[]{
																																																																																																																																																																						"CADASTRAR_DIZIMO"})
																																																																																																																																																																		.antMatchers(
																																																																																																																																																																				new String[]{
																																																																																																																																																																						"/edit/dizimo.xhtml"}))
																																																																																																																																																																								.hasAnyRole(
																																																																																																																																																																										new String[]{
																																																																																																																																																																												"EDITAR_DIZIMO"})
																																																																																																																																																																								.antMatchers(
																																																																																																																																																																										new String[]{
																																																																																																																																																																												"/list/dizimo.xhtml"}))
																																																																																																																																																																														.hasAnyRole(
																																																																																																																																																																																new String[]{
																																																																																																																																																																																		"LISTAR_DIZIMO"})
																																																																																																																																																																														.antMatchers(
																																																																																																																																																																																new String[]{
																																																																																																																																																																																		"/cad/cargo.xhtml"}))
																																																																																																																																																																																				.hasAnyRole(
																																																																																																																																																																																						new String[]{
																																																																																																																																																																																								"CADASTRAR_CARGO"})
																																																																																																																																																																																				.antMatchers(
																																																																																																																																																																																						new String[]{
																																																																																																																																																																																								"/edit/cargo.xhtml"}))
																																																																																																																																																																																										.hasAnyRole(
																																																																																																																																																																																												new String[]{
																																																																																																																																																																																														"EDITAR_CARGO"})
																																																																																																																																																																																										.antMatchers(
																																																																																																																																																																																												new String[]{
																																																																																																																																																																																														"/list/cargo.xhtml"}))
																																																																																																																																																																																																.hasAnyRole(
																																																																																																																																																																																																		new String[]{
																																																																																																																																																																																																				"LISTAR_CARGO"})
																																																																																																																																																																																																.antMatchers(
																																																																																																																																																																																																		new String[]{
																																																																																																																																																																																																				"/cad/diretoria.xhtml"}))
																																																																																																																																																																																																						.hasAnyRole(
																																																																																																																																																																																																								new String[]{
																																																																																																																																																																																																										"CADASTRAR_DIRETORIA"})
																																																																																																																																																																																																						.antMatchers(
																																																																																																																																																																																																								new String[]{
																																																																																																																																																																																																										"/edit/diretoria.xhtml"}))
																																																																																																																																																																																																												.hasAnyRole(
																																																																																																																																																																																																														new String[]{
																																																																																																																																																																																																																"EDITAR_DIRETORIA"})
																																																																																																																																																																																																												.antMatchers(
																																																																																																																																																																																																														new String[]{
																																																																																																																																																																																																																"/list/diretoria.xhtml"}))
																																																																																																																																																																																																																		.hasAnyRole(
																																																																																																																																																																																																																				new String[]{
																																																																																																																																																																																																																						"LISTAR_DIRETORIA"})
																																																																																																																																																																																																																		.and()).formLogin()
																																																																																																																																																																																																																				.loginPage(
																																																																																																																																																																																																																						"/login.xhtml")
																																																																																																																																																																																																																				.defaultSuccessUrl(
																																																																																																																																																																																																																						"/home.xhtml?logado=true"))
																																																																																																																																																																																																																								.failureUrl(
																																																																																																																																																																																																																										"/login.xhtml?invalid=true"))
																																																																																																																																																																																																																												.and()).logout()
																																																																																																																																																																																																																														.logoutRequestMatcher(
																																																																																																																																																																																																																																new AntPathRequestMatcher(
																																																																																																																																																																																																																																		"/logout"))
																																																																																																																																																																																																																														.and()).exceptionHandling()
																																																																																																																																																																																																																																.accessDeniedPage(
																																																																																																																																																																																																																																		"/acesso-negado.xhtml")
																																																																																																																																																																																																																																.authenticationEntryPoint(
																																																																																																																																																																																																																																		jsfLogin)
																																																																																																																																																																																																																																.authenticationEntryPoint(
																																																																																																																																																																																																																																		custom)
																																																																																																																																																																																																																																.accessDeniedHandler(
																																																																																																																																																																																																																																		jsfAccessDenied);
	}
}