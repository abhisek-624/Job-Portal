package com.generics;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class CustomListener extends TestBase implements ITestListener  {
	public Logger log = Logger.getLogger(CustomListener.class);

	public void onTestStart(ITestResult result) {
		log.info(result.getName() + " started");
		test = report.startTest(result.getName());

	}

	public void onTestSuccess(ITestResult result) {
		log.info(result.getName() + " Test is passed");
		test.log(LogStatus.PASS, result.getName() + " extent Test is passed");
	}

	
	public void onTestFailure(ITestResult result) {
		log.info(result.getName() + " Test is Failed");
		String pathForScreenshot = GeneralFunctions.captureScreenshot();
		log.info("Screenshot store at : " + pathForScreenshot);
		test.log(LogStatus.FAIL, result.getName() + " extent Test is Failed");
		test.log(LogStatus.INFO, result.getThrowable().getMessage());
		test.log(LogStatus.FAIL, test.addScreenCapture(pathForScreenshot));
		
//		test.addScreenCapture("")
		
	}

//	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getName() + " extent Test is skipped");
		
	}

//	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

//	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

//	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.endTest(test);
		report.flush();
	}

}
