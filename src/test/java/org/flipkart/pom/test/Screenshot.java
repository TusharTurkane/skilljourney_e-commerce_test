package org.flipkart.pom.test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Flow.Publisher;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Screenshot {
	
	public static void takeScreenShot(WebDriver driver, String filename) {
		
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		
		File tempFile = screenshot.getScreenshotAs(OutputType.FILE);
		
		String timestamp = getUniqueDateTimeStamp();
		try {
			FileUtils.copyFile(tempFile, new File("src\\test\\resources\\screenshots\\"+timestamp+"_"+filename));
			System.out.println("Screenshot Captured at "+timestamp);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getUniqueDateTimeStamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return now.format(formatter);
    }
	
	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		takeScreenShot(driver, "test.jpg");
	}

}
