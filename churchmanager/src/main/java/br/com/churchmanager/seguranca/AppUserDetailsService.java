package br.com.churchmanager.seguranca;

import br.com.churchmanager.bo.UsuarioBO;
import br.com.churchmanager.model.Pagina;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.seguranca.UsuarioSistema;
import br.com.churchmanager.util.cdi.CDIServiceLocator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
		ArrayList autorities = new ArrayList();
		Iterator arg3 = usuario.getPaginas().iterator();

		while (arg3.hasNext()) {
			Pagina grupo = (Pagina) arg3.next();
			String group = "ROLE_" + grupo.getNomeIdentificador();
			autorities.add(new SimpleGrantedAuthority(group));
		}

		return autorities;
	}
}