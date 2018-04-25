package br.com.churchmanager.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
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
	private static final boolean ASC = true;

	private EntityManager entityManager;
	private UsuarioDAO usuarioDAO;
	private PerfilDAO perfilDAO;
	private Perfil perfil;
	private Usuario usuario;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Perfil novoPerfil() {
		Perfil perfil = new PerfilBuilder().comNome("Admin").comDescricao("Perfil de administrador").ativo().builder();
		perfilDAO.salvar(perfil);
		return perfil;
	}

	private Usuario novoUsuario() {
		Usuario usuario = new UsuarioBuilder().comNome("Usuário de Teste").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("123").build();
		return usuario;
	}

	@Before
	public void setUp() {
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
		usuarioDAO = new UsuarioDAO(entityManager);
		perfilDAO = new PerfilDAO(entityManager);
		perfil = novoPerfil();
		usuario = novoUsuario();
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		usuarioDAO = null;
		perfilDAO = null;
		perfil = null;
		usuario = null;
	}

	@Test
	public void deveCadastrarUsuario() {

		long qtdRegistros = usuarioDAO.contagem("id", null, null, DISTINCT_TRUE);
		usuarioDAO.salvar(usuario);
		long totalAtualRegistros = usuarioDAO.contagem("id", null, null, DISTINCT_TRUE);

		Usuario usuarioDoBanco = usuarioDAO.buscarPorId(usuario.getId());

		assertEquals(qtdRegistros + 1, totalAtualRegistros);
		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getSenha(), usuarioDoBanco.getSenha());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());

	}

	@Test
	public void deveEditarUsuario() {
		usuarioDAO.salvar(usuario);

		usuario.setNomeCompleto("Nome editado");
		usuario.setEmail("email@editado.com");

		usuarioDAO.atualizar(usuario);

		Usuario usuarioDoBanco = usuarioDAO.porEmail("email@editado.com");

		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getSenha(), usuarioDoBanco.getSenha());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());

	}

	@Test
	public void deveRemoverUsuario() {
		usuarioDAO.salvar(usuario);
		long qtdRegistros = usuarioDAO.contagem("id", null, null, DISTINCT_TRUE);
		usuarioDAO.excluir(usuario);
		long totalAtualRegistros = usuarioDAO.contagem("id", null, null, DISTINCT_TRUE);
		usuario = usuarioDAO.buscarPorId(usuario.getId());
		assertNull(usuario);
		assertEquals(qtdRegistros - 1, totalAtualRegistros);
	}

	@Test
	public void deveListarUsuariosOrdenandoPorNome() {
		Usuario usuario1 = new UsuarioBuilder().comNome("Usuário Um").comEmail("usuario@um.com.br").comPerfil(perfil)
				.comSenha("111").build();
		Usuario usuario2 = new UsuarioBuilder().comNome("Usuário Dois").comEmail("usuario@dois.com.br")
				.comPerfil(perfil).comSenha("222").build();
		Usuario usuario3 = new UsuarioBuilder().comNome("Usuário Três").comEmail("usuario@tres.com.br")
				.comPerfil(perfil).comSenha("333").build();

		usuarioDAO.salvar(usuario1);
		usuarioDAO.salvar(usuario2);
		usuarioDAO.salvar(usuario3);

		List<Usuario> usuarios = usuarioDAO.listar(ASC, "nomeCompleto");
		int tamanhoDaLista = usuarios.size();

		assertEquals(3, tamanhoDaLista);
		assertEquals("Usuário Dois", usuarios.get(0).getNomeCompleto());
		assertEquals("Usuário Três", usuarios.get(1).getNomeCompleto());
		assertEquals("Usuário Um", usuarios.get(2).getNomeCompleto());

	}

	@Test
	public void deveBuscarUsuarioPorIdentificador() {

		usuarioDAO.salvar(usuario);
		Usuario usuarioDoBanco = usuarioDAO.buscarPorId(usuario.getId());

		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getSenha(), usuarioDoBanco.getSenha());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());

	}

	@Test
	public void deveEncontrarUsuarioPorEmail() {

		usuarioDAO.salvar(usuario);
		Usuario usuarioDoBanco = usuarioDAO.porEmail("usuario@teste.com.br");

		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getSenha(), usuarioDoBanco.getSenha());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());
	}

	@Test
	public void deveRetornarNuloSeNaoEncontrarUsuarioPorEmail() {
		Usuario usuarioDoBanco = usuarioDAO.porEmail("usuario@teste.com.br");
		assertNull(usuarioDoBanco);
	}

	@Test
	public void naoDeveTerEmailDuplicado() {
		expectedException.expect(ViolacaoDeRestricaoException.class);

		Usuario usuario1 = new UsuarioBuilder().comNome("Usuário de Teste 1").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("123").build();

		Usuario usuario2 = new UsuarioBuilder().comNome("Usuário de Teste 2").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("456").build();

		usuarioDAO.salvar(usuario1);
		usuarioDAO.salvar(usuario2);

	}

}
