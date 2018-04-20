package br.com.churchmanager.cadastros;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.churchmanager.bo.UsuarioBO;
import br.com.churchmanager.model.Usuario;
import br.com.churchmanager.pageobjects.UsuarioPage;
import br.com.churchmanager.util.Chrome;
import br.com.churchmanager.util.ContextoDaAplicacao;
import br.com.churchmanager.util.LoginUtil;
import br.com.churchmanager.util.WebDriverUtil;

public class CadastroDeUsuarioTest {
	
	private static WebDriver driver;

	@BeforeClass
	public static void inicializa() {
		driver = WebDriverUtil.driver(new Chrome());
		LoginUtil.efetuarLogin(driver);
	}

	@Before
	public void setUp() {
		//
	}
	
	@After
	public void tearDown(){
		
	}

	@Test
	public void deveAdicionarNovoUsuario() {
		UsuarioPage listagem = new UsuarioPage(driver);
		listagem.abre().novo().preencherNome("Usuário teste").preencherEmail("usuario@teste.com.br")
				.preencherSenha("123").preencherConfirmarSenha("123").selecionaPerfil().submete();
		assertTrue(listagem.contemMensagem("Cadastrado com sucesso!"));
	}

	@Ignore
	@Test
	public void deveValidarObrigatoriedadeDeEmail() {
		driver.get(ContextoDaAplicacao.cadastrar("usuario"));
		WebElement nome = driver.findElement(By.name("nome"));
		nome.sendKeys("Nome do Usuário");
		WebElement botao = driver.findElement(By.id("btn-salvar"));
		botao.click();
		assertTrue(driver.getPageSource().contains("Campo 'E-mail' é obrigatório!"));
	}
	
	@Inject 
	UsuarioBO usuarioBO;
	
	@Ignore
	@Test
	public void deveBuscarUsuarioPorEmail() {
		Usuario usuario = usuarioBO.porEmail("usuario@teste.com.br");
		Assert.assertEquals("Usuário teste", usuario.getNomeCompleto());
		Assert.assertEquals("usuario@teste.com.br", usuario.getEmail());
	}
	
	@Ignore
	@Test
	public void deveRemoverUsuario() {
		Usuario usuario = usuarioBO.porEmail("usuario@teste.com.br");
		usuarioBO.deletar(usuario);
		usuario = usuarioBO.porEmail("usuario@teste.com.br");
		Assert.assertNull(usuario);
	}

	@AfterClass
	public static void finaliza() {
		driver.close();
	}

}
