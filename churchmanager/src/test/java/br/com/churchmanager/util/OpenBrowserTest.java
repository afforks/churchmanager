package br.com.churchmanager.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class OpenBrowserTest {

	private static final String HTTP_GOOGLE_COM = "http://www.google.com/";

	private WebDriver driver;

	@Before
	public void onInitClass() {

	}

	@After
	public void onEndClass() {
		driver.close();
		driver.quit();
	}

	@Test
	public void deveAbrirFirefoxENavegaraParaGoogle() {
		driver = WebDriverUtil.driver(new Firefox());
		driver.get(HTTP_GOOGLE_COM);
		Assert.assertEquals("Google", driver.getTitle());
	}

	@Test
	public void deveAbrirChromeENavegaraParaGoogle() {
		driver = WebDriverUtil.driver(new Chrome());
		driver.get(HTTP_GOOGLE_COM);
		Assert.assertEquals("Google", driver.getTitle());
	}

}
