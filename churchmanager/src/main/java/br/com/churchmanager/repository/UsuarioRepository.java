package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Usuario;

@Repository(forEntity = Usuario.class)
public interface UsuarioRepository extends EntityRepository<Usuario, Long> {

	Usuario findByEmail(String email);

}