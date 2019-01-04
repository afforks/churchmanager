package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository(forEntity = Usuario.class)
public interface UsuarioRepository extends EntityRepository<Usuario, Long> {

	Usuario findByEmail(String email);

	LazyDataModel<Usuario> filtrar(UsuarioRepository repository);

	Usuario porEmail(String email);
}