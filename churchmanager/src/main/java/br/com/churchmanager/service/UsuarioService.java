package br.com.churchmanager.service;

import br.com.churchmanager.model.Usuario;

public interface UsuarioService extends Service<Usuario> {

	Usuario findByEmail(String email);

}
