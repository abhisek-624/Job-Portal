package com.generics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config;
	public static Logger log;
	public static WebDriverWait wait;
	public static ExtentTest test;
	public static ExtentReports report;
	public static ExcelReader excel;



	@BeforeClass
	public void setUp() {
		log = Logger.getLogger(TestBase.class);
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\Assets\\Log4j.properties");
		
		report = new ExtentReports(System.getProperty("user.dir")+"//reports//TestReportResults.html");

		
		config = new Properties();
		

		try {
			config.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("launching chrome browser");
		}
		else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("launching firefox browser");
		}


		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(config.getProperty("url"));
		log.info("Navigating to The URL ");
		wait = new WebDriverWait(driver, 20);
		
	}
	
	@AfterClass
	public static void tearDown() {
		
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		if(driver!=null)
		{	driver.close();
			log.info("Browser closed");
		}
		}
	}


