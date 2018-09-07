package br.com.churchmanager.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox implements DriverStrategy {

	@Override
	public WebDriver getDriver() {
		return new FirefoxDriver();
	}
}
