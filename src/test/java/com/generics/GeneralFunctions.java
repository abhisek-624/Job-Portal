package com.generics;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

public class GeneralFunctions extends TestBase {

	public static void click(WebElement element) {
		try {
			explicitWait(element, 20);
			element.click();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static void clickWithJS(WebElement element) {
		try {
			explicitWait(element, 10);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendKeys(WebElement element, String value) {
		try {
			explicitWait(element, 10);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public static boolean visibilityCheck(WebElement element) {
		boolean verify=false;
		try {
			explicitWait(element, 10);
			verify=element.isDisplayed();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return verify;
	}
	
	public String generateRandomEmail() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000000);
		String s = "edermxtest" + randomInt + "@yopmail.com";
		return s;
	}
	
	public String generateRandomFirstName() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		String s = "RandomFirstname" + randomInt;
		return s;
	}
	
	public String generateRandomLastName() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		String s = "RandomLastname" + randomInt;
		return s;
	}
	
	public static String captureScreenshot() {

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yy_hh_mm_ss_");
		LocalDateTime now = LocalDateTime.now();
		String filename = dtf.format(now) + "Screenshot.png";
		System.out.println(filename);
		String destFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\images\\" + filename;
		File Dest = new File(destFilePath);
		try {
			FileUtils.copyFile(screenshot, Dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("screenshot captured");
		return destFilePath;

	}

	public static void explicitWait(WebElement element, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public static void explicitWaitforClick(WebElement element, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	@DataProvider(name = "dp")
	public Object[][] getDataAsMethod(Method m) {
		excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\Assets\\TestData.xlsx");
		return getDataFromExcel(excel, m.getName());
	}

	@DataProvider(name = "dp2")
	public Object[][] getDataAsMethod1(Method m) {
		excel = new ExcelReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Assets\\bulk_Candidates.xlsx");
		return getDataFromExcel(excel, m.getName());
	}

	public Object[][] getDataFromExcel(ExcelReader excel, String sheetname) {

		String sheet = sheetname;
		int rows = excel.getRowCount(sheet);
		int cols = excel.getColumnCount(sheet);
		Object[][] data = new Object[rows - 1][1];

		for (int rownum = 2; rownum <= rows; rownum++) {
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int colnum = 0; colnum < cols; colnum++) {
				{
					table.put(excel.getCellData(sheet, colnum, 1), excel.getCellData(sheet, colnum, rownum));
					data[rownum - 2][0] = table;
//					System.out.println(data[rownum-2][0]);
				}
			}
		}

		return data;
	}

}
