package TestNG_Parallel;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import io.appium.java_client.MobileBy;

import org.testng.annotations.Test;

public class run_parallal_test extends BS_Config_Parallal {
	@Test
	public void test() throws IOException, InterruptedException  {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		WebElement searchElement=wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
		searchElement.click();
		WebElement insertElement=wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
		insertElement.sendKeys("Browserstack");
		      Thread.sleep(5000);
		
	}

}
