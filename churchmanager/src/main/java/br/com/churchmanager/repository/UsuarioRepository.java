package br.com.churchmanager.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.jsf.primefaces.LazyDataModel;

@Repository
public interface UsuarioRepository extends EntityRepository<Usuario, Long> {

	public Usuario findByEmail(String email);

	public LazyDataModel<Usuario> filtrar(UsuarioRepository repository);

	public Usuario porEmail(String email);
}