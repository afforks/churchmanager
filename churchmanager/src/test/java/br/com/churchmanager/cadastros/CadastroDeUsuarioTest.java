package br.com.churchmanager.cadastros;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import br.com.churchmanager.builder.PaginaBuilder;
import br.com.churchmanager.builder.PerfilBuilder;
import br.com.churchmanager.builder.UsuarioBuilder;
import br.com.churchmanager.dao.PaginaDAO;
import br.com.churchmanager.dao.PerfilDAO;
import br.com.churchmanager.dao.UsuarioDAO;
import br.com.churchmanager.model.Pagina;
import br.com.churchmanager.model.Perfil;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.pageobjects.UsuarioPage;
import br.com.churchmanager.util.Chrome;
import br.com.churchmanager.util.ContextoDaAplicacao;
import br.com.churchmanager.util.JPAUtil;
import br.com.churchmanager.util.LoginUtil;
import br.com.churchmanager.util.WebDriverUtil;

public class CadastroDeUsuarioTest {

	private static WebDriver driver;
	private static EntityManager entityManager;
	
	private static UsuarioDAO usuarioDAO;
	private static PerfilDAO perfilDAO;
	private static PaginaDAO paginaDAO;

	private static Usuario usuario;
	private static Perfil perfil;

	@AfterClass
	public static void finaliza() {
		
		removerDados();
		
		usuarioDAO = null;
		perfilDAO = null;
		usuario = null;
		// entityManager.getTransaction().rollback();
		entityManager.close();
		driver.close();
	}
	
	private static void removerDados() {
		paginaDAO.listar(true).forEach(p->paginaDAO.excluir(p));
		usuarioDAO.excluir(usuario);
		perfilDAO.excluir(perfil);
	}

	@BeforeClass
	public static void inicializa() {
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();

		novoUsuario();

		driver = WebDriverUtil.driver(new Chrome());

		LoginUtil.efetuarLogin(driver);
	}

	@Before
	public void setUp() {
	}

	private static void novoUsuario() {

		Pagina cadUsuario = new PaginaBuilder().comNome("Cadastrar usuário")
				.comDescricao("Descrição da página").build();
		Pagina editUsuario = new PaginaBuilder().comNome("Editar usuário")
				.comDescricao("Descrição da página").build();
		Pagina listUsuario = new PaginaBuilder().comNome("Listar usuário")
				.comDescricao("Descrição da página").build();

		paginaDAO = new PaginaDAO(entityManager);
		perfilDAO = new PerfilDAO(entityManager);
		perfil = new PerfilBuilder().comNome("Admin")
				.comDescricao("Perfil de administrador").ativo()
				.comPaginas(cadUsuario, editUsuario, listUsuario).builder();
		perfilDAO.salvar(perfil);

		usuarioDAO = new UsuarioDAO(entityManager);
		usuario = new UsuarioBuilder().comNome("Usuário de Teste").comEmail("user@admin").comPerfil(perfil)
				.comSenha(new Md5PasswordEncoder().encodePassword("123", null))
				.comPaginas(cadUsuario, editUsuario, listUsuario).build();
		usuarioDAO.salvar(usuario);
		
		//COMMIT 
		entityManager.getTransaction().commit();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void deveAdicionarNovoUsuario() {
		UsuarioPage listagem = new UsuarioPage(driver);
		listagem.abre().novo().preencherNome("Usuário teste").preencherEmail("usuario@teste.com.br")
				.preencherSenha("123").preencherConfirmarSenha("123").selecionaPerfil().submete();
		assertTrue(listagem.contemMensagem("Cadastrado com sucesso!"));
	}

	@Test
	public void deveValidarObrigatoriedadeDeEmail() {
		driver.get(ContextoDaAplicacao.cadastrar("usuario"));
		WebElement nome = driver.findElement(By.name("nome"));
		nome.sendKeys("Nome do Usuário");
		WebElement botao = driver.findElement(By.id("btn-salvar"));
		botao.click();
		assertTrue(driver.getPageSource().contains("Campo 'E-mail' é obrigatório!"));
	}
}
