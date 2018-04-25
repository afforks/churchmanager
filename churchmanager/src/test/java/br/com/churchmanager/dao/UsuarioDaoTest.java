package br.com.churchmanager.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.churchmanager.builder.PerfilBuilder;
import br.com.churchmanager.builder.UsuarioBuilder;
import br.com.churchmanager.exception.ViolacaoDeRestricaoException;
import br.com.churchmanager.model.Perfil;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.util.JPAUtil;

public class UsuarioDaoTest {

	private static final boolean DISTINCT_TRUE = true;
	private EntityManager entityManager;
	private UsuarioDAO usuarioDAO;
	private PerfilDAO perfilDAO;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
		usuarioDAO = new UsuarioDAO(entityManager);
		perfilDAO = new PerfilDAO(entityManager);
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		usuarioDAO = null;
	}

	@Test
	public void deveCadastrarUsuario() {
		Perfil perfil = new PerfilBuilder().comNome("Admin").builder();
		perfilDAO.salvar(perfil);
		long qtdRegistros = usuarioDAO.contagem("id", null, null, DISTINCT_TRUE);
		Usuario usuario = new UsuarioBuilder().comNome("Usuário de Teste").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("123").build();
		usuarioDAO.salvar(usuario);
		long totalAtualRegistros = usuarioDAO.contagem("id", null, null, DISTINCT_TRUE);
		
		Usuario usuarioDoBanco = usuarioDAO.buscarPorId(usuario.getId());
		
		Assert.assertEquals(qtdRegistros + 1, totalAtualRegistros);
		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());

	}

	@Test
	public void deveEditarUsuario() {
		Perfil perfil = new PerfilBuilder().comNome("Admin").builder();
		perfilDAO.salvar(perfil);
		Usuario usuario = new UsuarioBuilder().comNome("Usuário de Teste").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("123").build();
		usuarioDAO.salvar(usuario);
		usuario.setNomeCompleto("Nome editado");
		usuario.setEmail("email@editado.com");
		usuarioDAO.atualizar(usuario);
		Usuario usuarioDoBanco = usuarioDAO.porEmail("email@editado.com");
		
		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());

	}

	@Test
	public void deveRemoverUsuario() {
		Perfil perfil = new PerfilBuilder().comNome("Admin").builder();
		perfilDAO.salvar(perfil);
		Usuario usuario = new UsuarioBuilder().comNome("Usuário de Teste").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("123").build();
		usuarioDAO.salvar(usuario);
		long qtdRegistros = usuarioDAO.contagem("id", null, null, DISTINCT_TRUE);
		usuarioDAO.excluir(usuario);
		long totalAtualRegistros = usuarioDAO.contagem("id", null, null, DISTINCT_TRUE);
		usuario = usuarioDAO.buscarPorId(usuario.getId());
		Assert.assertNull(usuario);
		Assert.assertEquals(qtdRegistros - 1, totalAtualRegistros);
	}

	@Test
	public void deveListarUsuarios() {

	}

	@Test
	public void deveBuscarUsuarioPorIdentificador() {
		Perfil perfil = new PerfilBuilder().comNome("Admin").builder();
		perfilDAO.salvar(perfil);
		Usuario usuario = new UsuarioBuilder().comNome("Usuário de Teste").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("123").build();
		usuarioDAO.salvar(usuario);
		Usuario usuarioDoBanco = usuarioDAO.buscarPorId(usuario.getId());
		
		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());

	}

	@Test
	public void deveEncontrarUsuarioPorEmail() {
		Perfil perfil = new PerfilBuilder().comNome("Admin").builder();
		perfilDAO.salvar(perfil);
		Usuario usuario = new UsuarioBuilder().comNome("Usuário de Teste").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("123").build();
		usuarioDAO.salvar(usuario);
		Usuario usuarioDoBanco = usuarioDAO.porEmail("usuario@teste.com.br");
		
		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());
	}

	@Test
	public void deveRetornarNuloSeNaoEncontrarUsuarioPorEmail() {
		Usuario usuarioDoBanco = usuarioDAO.porEmail("usuario@teste.com.br");
		Assert.assertNull(usuarioDoBanco);
	}
	
	@Test
	public void naoDeveTerEmailDuplicado() {
		expectedException.expect(ViolacaoDeRestricaoException.class);

		Perfil perfil = new PerfilBuilder().comNome("Admin").builder();
		perfilDAO.salvar(perfil);
		
		Usuario usuario1 = new UsuarioBuilder().comNome("Usuário de Teste 1").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("123").build();

		Usuario usuario2 = new UsuarioBuilder().comNome("Usuário de Teste 2").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("456").build();
		
		usuarioDAO.salvar(usuario1);
		usuarioDAO.salvar(usuario2);
		
	}

}
