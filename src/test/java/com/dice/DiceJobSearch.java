package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		//this line does the same as below:
		//System.setProperty("webdriver.chrome.driver",
				//"C:/Users/filiz/Documents/selenium dependencies/drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		//fullscreen
		//for mac:
		//driver.manage().window().fullscreen
		//for windows
		driver.manage().window().maximize();
		//set wait time in case webpage is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//automation step1:
		//launc website dice.com
		String url="http://dice.com";
		driver.get(url);
		
		String actualTitle=driver.getTitle();
		String expected="Job Search for Technology Professionals | Dice.com";
		if(actualTitle.equals(expected))
		{
			System.out.println("dice homepage successfully loaded");
		}else {
			System.out.println("step fail.Dice homepage did not load");
			throw new RuntimeException();
		}
		
		String keyword="java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location="78717";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		driver.findElement(By.id("findTechJobs")).click();
		String count=driver.findElement(By.id("posiCountMobileId")).getText();
		System.out.println(count);
		//ensure count is more than zero
//		int countResult=Integer.parseInt(count.replace(",", ""));//if there is , in the string it replaces it with nothing/space
//		if(countResult>0)
//		{
//			System.out.println("Keyword "+keyword+" search returned "+ countResult+ "results in "+location);
//		}else {
//			System.out.println("step failed");
//		}
	driver.close();
	System.out.println("test completed at "+LocalDateTime.now());
	}
}
