package br.com.churchmanager.util;

import org.openqa.selenium.WebDriver;

public class WebDriverUtil {

	public static WebDriver driver(DriverStrategy driver) {
		WebDriver webDriver = driver.getDriver();
		webDriver.manage().window().maximize();
		return webDriver;
	}
}
