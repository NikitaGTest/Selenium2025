package com.practiceApp.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practiceApp.resources.ExtentReportsNG;

public class Listners extends BaseTest implements ITestListener {
	
	
	ExtentReports extent=ExtentReportsNG.getReportObject();
	ExtentTest test ;

    @Override
    public void onTestStart(ITestResult result) {
    	
    	test=extent.createTest(result.getMethod().getMethodName());
    	
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	
    	test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	
    	
        test.fail(result.getThrowable());             // <- required

    	String screenshotpath = null;
    	
    	try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 try {
			 screenshotpath=captureScreenshot(result.getMethod().getMethodName(),driver);
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
    	test.addScreenCaptureFromPath(screenshotpath,result.getMethod().getMethodName());
    	
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional - used rarely
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    	
    	extent.flush();
    }
}
