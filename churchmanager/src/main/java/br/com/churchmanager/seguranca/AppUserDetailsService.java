package br.com.churchmanager.seguranca;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.churchmanager.bo.UsuarioBO;
import br.com.churchmanager.model.Pagina;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioBO usuarioBO = (UsuarioBO) CDIServiceLocator.getBean(UsuarioBO.class);
		Usuario usuario = usuarioBO.porEmail(email);
		UsuarioSistema user = null;
		if (usuario != null) {
			user = new UsuarioSistema(usuario, this.getPaginas(usuario));
			return user;
		} else {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
	}

	private Collection<? extends GrantedAuthority> getPaginas(Usuario usuario) {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Pagina grupo : usuario.getPaginas()) {
			String group = "ROLE_" + grupo.getNomeIdentificador();
			authorities.add(new SimpleGrantedAuthority(group));
		}

		return authorities;
	}
}