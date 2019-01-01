package br.com.churchmanager.repository;

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
import br.com.churchmanager.model.Status;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.repository.PerfilRepository;
import br.com.churchmanager.repository.UsuarioRepository;
import br.com.churchmanager.util.JPAUtil;

public class UsuarioDaoTest {

	private static final boolean DISTINCT_TRUE = true;
	private static final boolean ASC = true;

	private EntityManager entityManager;
	private UsuarioRepository usuarioRepository;
	private PerfilRepository perfilRepository;
	private Perfil perfil;
	private Usuario usuario;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Perfil novoPerfil() {
		Perfil perfil = new PerfilBuilder().comNome("Admin").comDescricao("Perfil de administrador").ativo().builder();
		perfilRepository.save(perfil);
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
		usuarioRepository = new UsuarioRepository(entityManager);
		perfilRepository = new PerfilRepository(entityManager);
		perfil = novoPerfil();
		usuario = novoUsuario();
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
		entityManager.close();
		usuarioRepository = null;
		perfilRepository = null;
		perfil = null;
		usuario = null;
	}

	@Test
	public void deveCadastrarUsuario() {

		long qtdRegistros = usuarioRepository.contagem("id", null, null, DISTINCT_TRUE);
		usuarioRepository.save(usuario);
		long totalAtualRegistros = usuarioRepository.contagem("id", null, null, DISTINCT_TRUE);

		Usuario usuarioDoBanco = usuarioRepository.findBy(usuario.getId());

		assertEquals(qtdRegistros + 1, totalAtualRegistros);
		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getSenha(), usuarioDoBanco.getSenha());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());

	}

	@Test
	public void deveEditarUsuario() {
		usuarioRepository.save(usuario);

		usuario.setNomeCompleto("Nome editado");
		usuario.setEmail("email@editado.com");

		usuarioRepository.save(usuario);

		Usuario usuarioDoBanco = usuarioRepository.porEmail("email@editado.com");

		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getSenha(), usuarioDoBanco.getSenha());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());

	}

	@Test
	public void deveRemoverUsuario() {
		usuarioRepository.save(usuario);
		long qtdRegistros = usuarioRepository.contagem("id", null, null, DISTINCT_TRUE);
		usuarioRepository.remove(usuario);
		long totalAtualRegistros = usuarioRepository.contagem("id", null, null, DISTINCT_TRUE);
		usuario = usuarioRepository.findBy(usuario.getId());
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

		usuarioRepository.save(usuario1);
		usuarioRepository.save(usuario2);
		usuarioRepository.save(usuario3);

		List<Usuario> usuarios = usuarioRepository.findAll(ASC, "nomeCompleto");
		int tamanhoDaLista = usuarios.size();

		assertEquals(3, tamanhoDaLista);
		assertEquals(usuario2.getNomeCompleto(), usuarios.get(0).getNomeCompleto());
		assertEquals(usuario3.getNomeCompleto(), usuarios.get(1).getNomeCompleto());
		assertEquals(usuario1.getNomeCompleto(), usuarios.get(2).getNomeCompleto());

	}

	@Test
	public void deveBuscarUsuarioPorIdentificador() {

		usuarioRepository.save(usuario);
		Usuario usuarioDoBanco = usuarioRepository.findBy(usuario.getId());

		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getSenha(), usuarioDoBanco.getSenha());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());

	}

	@Test
	public void deveEncontrarUsuarioPorEmail() {

		usuarioRepository.save(usuario);
		Usuario usuarioDoBanco = usuarioRepository.porEmail("usuario@teste.com.br");

		assertEquals("id", usuario.getId(), usuarioDoBanco.getId());
		assertEquals("nome", usuario.getNomeCompleto(), usuarioDoBanco.getNomeCompleto());
		assertEquals("email", usuario.getEmail(), usuarioDoBanco.getEmail());
		assertEquals("senha", usuario.getSenha(), usuarioDoBanco.getSenha());
		assertEquals("perfil", usuario.getPerfil(), usuarioDoBanco.getPerfil());
	}

	@Test
	public void deveRetornarNuloSeNaoEncontrarUsuarioPorEmail() {
		Usuario usuarioDoBanco = usuarioRepository.porEmail("usuario@teste.com.br");
		assertNull(usuarioDoBanco);
	}

	@Test
	public void naoDeveTerEmailDuplicado() {
		expectedException.expect(ViolacaoDeRestricaoException.class);

		Usuario usuario1 = new UsuarioBuilder().comNome("Usuário de Teste 1").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("123").build();

		Usuario usuario2 = new UsuarioBuilder().comNome("Usuário de Teste 2").comEmail("usuario@teste.com.br")
				.comPerfil(perfil).comSenha("456").build();

		usuarioRepository.save(usuario1);
		usuarioRepository.save(usuario2);

	}
	
	@Test
	public void deveAlterarStatus() {
		Usuario usuario1 = new UsuarioBuilder().comNome("Usuário de Teste 1").comEmail("usuario1@um.com.br")
				.comPerfil(perfil).comSenha("123").ativo().build();

		Usuario usuario2 = new UsuarioBuilder().comNome("Usuário de Teste 2").comEmail("usuario2@dois.com.br")
				.comPerfil(perfil).comSenha("456").inativo().build();

		usuarioRepository.save(usuario1);
		usuarioRepository.save(usuario2);
		
		usuarioRepository.saveStatus(usuario1);
		usuarioRepository.saveStatus(usuario2);
		
		Usuario usuarioBanco1 = usuarioRepository.findBy(usuario1.getId());
		Usuario usuarioBanco2 = usuarioRepository.findBy(usuario2.getId());
		
		assertEquals(Status.INATIVO, usuarioBanco1.getStatus());
		assertEquals(Status.ATIVO, usuarioBanco2.getStatus());
		
		
	}

}
