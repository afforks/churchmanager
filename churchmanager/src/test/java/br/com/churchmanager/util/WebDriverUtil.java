package br.com.churchmanager.util;

import org.openqa.selenium.WebDriver;

public class WebDriverUtil {

	public static WebDriver driver(DriverStrategy driver) {
		return driver.getDriver();
	}
}
