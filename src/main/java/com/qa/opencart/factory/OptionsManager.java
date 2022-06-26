package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	ChromeOptions co;
	FirefoxOptions fo;
	Properties prop;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("enableVNC", true);
		}
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.setHeadless(true);
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("enableVNC", true);
		}
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.setHeadless(true);
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            fo.addArguments("--incognito");
		}
		return fo;
	}
}
