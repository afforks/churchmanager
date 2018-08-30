package br.com.churchmanager.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements DriverStrategy {

	public Chrome() {
		System.setProperty("webdriver.chrome.driver", "//home/junior/webdriver/chromedriver");
	}

	@Override
	public WebDriver getDriver() {
		return new ChromeDriver();
	}

}
