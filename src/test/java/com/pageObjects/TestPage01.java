package com.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.generics.TestBase;

public class TestPage01 extends TestBase {

	public WebDriver driver;
	public static Logger log = Logger.getLogger(TestPage01.class);

	
	public TestPage01(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void testing() {
		log.info("Type code here.");
	}

}
