package br.com.churchmanager.security;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.churchmanager.util.Chrome;
import br.com.churchmanager.util.ContextoDaAplicacao;
import br.com.churchmanager.util.WebDriverUtil;

public class TestarTelaLogin {

	private static WebDriver driver;
	private static String URL;

	@BeforeClass
	public static void inicializa() {
		driver = WebDriverUtil.driver(new Chrome());
		URL = ContextoDaAplicacao.login();
		driver.get(URL);
	}

	@Before
	public void setUp() {

	}

	@AfterClass
	public static void finaliza() {
		driver.close();
	}

	@Test
	public void deveEfeturarLogin() {

		WebElement email = driver.findElement(By.id("username"));
		email.sendKeys("user@admin");

		WebElement senha = driver.findElement(By.id("password"));
		senha.sendKeys("123");

		WebElement btnLogar = driver.findElement(By.id("btn-logar"));
		btnLogar.click();

		assertTrue(driver.getCurrentUrl().contains("/home.xhtml"));
	}

}
