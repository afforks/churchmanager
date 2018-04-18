package br.com.churchmanager.cadastro.dao;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.churchmanager.dao.UsuarioDAO;
import br.com.churchmanager.model.Usuario;

public class UsuarioDaoTest {

	@Inject
	private EntityManager entitymanager;
	
	@Inject
	private UsuarioDAO dao;

	@Before
	public void setUp() {
		entitymanager.getTransaction().begin();
	}

	@Test
	public void deveEncontrarClientePorEmail() {

		Usuario usuario = new Usuario("Usuário de Teste", "usuario@teste.com.br");
		entitymanager.persist(usuario);

		Usuario usuarioDoBanco = dao.porEmail("usuario@teste.com.br");

		assertEquals(usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals(usuario.getEmail(), usuarioDoBanco.getEmail());

	}

	@Test
	public void naoDeveEncontrarClientePorEmail() {

		Usuario usuario = new Usuario("Usuario sem email", "sememail@email.com");
		entitymanager.persist(usuario);

		Usuario usuarioDoBanco = dao.porEmail("sememail@email.com");

		assertEquals(usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals(usuario.getEmail(), usuarioDoBanco.getEmail());

	}

	@After
	public void tearDown() {
		entitymanager.getTransaction().rollback();
		entitymanager.close();

	}

}
