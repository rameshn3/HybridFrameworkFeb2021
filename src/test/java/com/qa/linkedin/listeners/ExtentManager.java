package com.qa.linkedin.listeners;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * document comment
 * @author LENOVO
 * date: 8-Feb-2021
 * this class explains where to store my extent report
 *
 */

public class ExtentManager {
	
private static ExtentReports extent;
	
public static ExtentReports createInstance() {
	String fileName=getReportName();
	//String directory=System.getProperty("user.dir")+ "/target/extentreports/";
	String directory="target/extentreports/";
	new File(directory).mkdirs();
	String path=directory + fileName;
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
    htmlReporter.config().setTheme(Theme.STANDARD);
    htmlReporter.config().setDocumentTitle("Linkedin Automation Test Reports");
    htmlReporter.config().setEncoding("utf-8");
    htmlReporter.config().setReportName("Linkedin Automation Test Results");
    extent = new ExtentReports();
    extent.setSystemInfo("Automation Tester", "Ramesh");
    extent.setSystemInfo("Organization", "linkedin");
    extent.setSystemInfo("Build no", "QA-1234");
    extent.attachReporter(htmlReporter);
    
    return extent;
}

public static String getReportName() {
	Date d = new Date();
	String fileName = "ExtentReport"+"-"+ d.toString().replace(":", "_").replace(" ", "_") + ".html";
	return fileName;
}


}
