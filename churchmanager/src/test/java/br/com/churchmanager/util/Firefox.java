package br.com.churchmanager.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox implements DriverStrategy {

	@Override
	public WebDriver getDriver() {
		System.setProperty("webdriver.gecko.driver", "//home/junior/webdriver/firefox/18/geckodriver");
		return new FirefoxDriver();
	}
}
