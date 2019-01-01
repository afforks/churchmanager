package br.com.churchmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.churchmanager.jsf.primefaces.LazyDataModel;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.model.filter.Filter;
import br.com.churchmanager.repository.UsuarioRepository;
import br.com.churchmanager.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioRepository repository;

	public Usuario save(Usuario usuario) {
		this.validar(usuario);
		return this.repository.save(usuario);
	}

	public Usuario update(Usuario usuario) {
		this.validar(usuario);
		return this.repository.save(usuario);
	}

	public void delete(Usuario usuario) {
		this.repository.remove(usuario);
	}

	public List<Usuario> findAll() {
		return this.repository.findAll();
	}

	public void validar(Usuario usuario) {
		//
	}

	public Usuario findBy(Long id) {
		return this.repository.findBy(id);
	}

	public Usuario findByEmail(String email) {
		return this.repository.findByEmail(email);
	}

	@Override
	public EntityRepository<Usuario, Long> getRepository() {
		return repository;
	}

	@Override
	public void updateStatus(Usuario t) {
		t.alterarStatus();
		update(t);
	}

	@Override
	public LazyDataModel<Usuario> lazyList(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> autoComplete(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}