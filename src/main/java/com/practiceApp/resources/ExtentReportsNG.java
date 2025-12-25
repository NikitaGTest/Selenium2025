package com.practiceApp.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

	
	public static ExtentReports getReportObject() {
		
		
        String path = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Practice application ");
        reporter.config().setDocumentTitle("Practice Application testing ");
        
        
        ExtentReports extent= new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Reporter Name", "Nikita Fondekar");
        extent.setSystemInfo("Welcome", "OK");
        return extent;
        
        
	}
	
	
}
