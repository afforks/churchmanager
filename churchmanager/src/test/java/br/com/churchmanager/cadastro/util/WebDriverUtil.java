package br.com.churchmanager.cadastro.util;

import org.openqa.selenium.WebDriver;

import br.com.churchmanager.cadastro.model.DriverStrategy;

public class WebDriverUtil {

	public static WebDriver driver(DriverStrategy driver) {
		return driver.getDriver();
	}
}
