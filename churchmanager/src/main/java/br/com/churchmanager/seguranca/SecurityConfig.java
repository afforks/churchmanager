package br.com.churchmanager.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		http.csrf().disable().headers().frameOptions().sameOrigin().and().authorizeRequests()
				.antMatchers("/form/**", "/WEB-INF/template/**").denyAll()
				.antMatchers("/login.xhtml", "/erro.xhtml", "/javax.faces.resource/**",
						"/nao-encontrado.xhtml", "/bug.xhtml").permitAll()
				
				.antMatchers("/home.xhtml", "/acesso-negado.xhtml").authenticated()
				
				.antMatchers("/cad/pagina.xhtml").hasAnyRole("CADASTRAR_PAGINA").antMatchers("/edit/pagina.xhtml")
				.hasAnyRole("EDITAR_PAGINA").antMatchers("/list/pagina.xhtml").hasAnyRole("LISTAR_PAGINA")
				.antMatchers("/cad/perfil.xhtml").hasAnyRole("CADASTRAR_PERFIL").antMatchers("/edit/perfil.xhtml")
				.hasAnyRole("EDITAR_PERFIL").antMatchers("/list/perfil.xhtml").hasAnyRole("LISTAR_PERFIL")
				.antMatchers("/cad/usuario.xhtml").hasAnyRole("CADASTRAR_USUARIO").antMatchers("/edit/usuario.xhtml")
				.hasAnyRole("EDITAR_USUARIO").antMatchers("/list/usuario.xhtml").hasAnyRole("LISTAR_USUARIO")
				.antMatchers("/cad/pessoa.xhtml").hasAnyRole("CADASTRAR_PESSOA").antMatchers("/edit/pessoa.xhtml")
				.hasAnyRole("EDITAR_PESSOA").antMatchers("/list/pessoa.xhtml").hasAnyRole("LISTAR_PESSOA")
				.antMatchers("/cad/atividade.xhtml").hasAnyRole("CADASTRAR_ATIVIDADE_ECLESIASTICA")
				.antMatchers("/edit/atividade.xhtml").hasAnyRole("EDITAR_ATIVIDADE_ECLESIASTICA")
				.antMatchers("/list/atividade.xhtml").hasAnyRole("LISTAR_ATIVIDADE_ECLESIASTICA")
				.antMatchers("/cad/categoria.xhtml").hasAnyRole("CADASTRAR_CATEGORIA_MOVIMENTACAO")
				.antMatchers("/edit/categoria.xhtml").hasAnyRole("EDITAR_CATEGORIA_MOVIMENTACAO")
				.antMatchers("/list/categoria.xhtml").hasAnyRole("LISTAR_CATEGORIA_MOVIMENTACAO")
				.antMatchers("/cad/movimentacao.xhtml").hasAnyRole("CADASTRAR_MOVIMENTACAO")
				.antMatchers("/edit/movimentacao.xhtml").hasAnyRole("EDITAR_MOVIMENTACAO")
				.antMatchers("/list/movimentacao.xhtml").hasAnyRole("LISTAR_MOVIMENTACAO")
				.antMatchers("/cad/evento.xhtml").hasAnyRole("CADASTRAR_EVENTO").antMatchers("/edit/evento.xhtml")
				.hasAnyRole("EDITAR_EVENTO").antMatchers("/list/evento.xhtml").hasAnyRole("LISTAR_EVENTO")
				.antMatchers("/cad/patrimonio.xhtml").hasAnyRole("CADASTRAR_PATRIMONIO")
				.antMatchers("/edit/patrimonio.xhtml").hasAnyRole("EDITAR_PATRIMONIO")
				.antMatchers("/list/patrimonio.xhtml").hasAnyRole("LISTAR_PATRIMONIO").antMatchers("/cad/dizimo.xhtml")
				.hasAnyRole("CADASTRAR_DIZIMO").antMatchers("/edit/dizimo.xhtml").hasAnyRole("EDITAR_DIZIMO")
				.antMatchers("/list/dizimo.xhtml").hasAnyRole("LISTAR_DIZIMO").antMatchers("/cad/cargo.xhtml")
				.hasAnyRole("CADASTRAR_CARGO").antMatchers("/edit/cargo.xhtml").hasAnyRole("EDITAR_CARGO")
				.antMatchers("/list/cargo.xhtml").hasAnyRole("LISTAR_CARGO").antMatchers("/cad/diretoria.xhtml")
				.hasAnyRole("CADASTRAR_DIRETORIA").antMatchers("/edit/diretoria.xhtml").hasAnyRole("EDITAR_DIRETORIA")
				.antMatchers("/list/diretoria.xhtml").hasAnyRole("LISTAR_DIRETORIA").and().formLogin()
				.loginPage("/login.xhtml").defaultSuccessUrl("/home.xhtml?logado=true")
				.failureUrl("/login.xhtml?invalid=true").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and().exceptionHandling()
				.accessDeniedPage("/acesso-negado.xhtml").authenticationEntryPoint(jsfLogin)
				.authenticationEntryPoint(custom).accessDeniedHandler(jsfAccessDenied);
	}
}