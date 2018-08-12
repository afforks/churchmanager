package br.com.churchmanager.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Firefox implements DriverStrategy {

	@Override
	public WebDriver getDriver() {
		System.setProperty("webdriver.gecko.driver", "//home/junior/Downloads/geckodriver");
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability("marionette", true);
		return new FirefoxDriver(dc);
	}

}
