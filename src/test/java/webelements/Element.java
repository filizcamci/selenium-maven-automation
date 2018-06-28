package webelements;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Element {
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		System.out.println("setting up WebDriver before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	
	}
	@Test
	public void webElementExamples(){
		driver.get(
				"https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");
	WebElement email=driver.findElement(By.name("Email"));	
	String value = email.getAttribute("value");
	String maxValue = email.getAttribute("maxlength");
	String type = email.getAttribute("type");
	String tag = email.getTagName();
	
	boolean b = email.isEnabled();
	
	System.out.println(value + " " + maxValue+ " " + type+ " " +tag + " " +b);
	assertEquals(value, "youremail@mail.com");
	email.clear();
	
	WebElement country=driver.findElement(By.id("Address_Country"));
	Select selectCountry=new Select(country);
	String option1=selectCountry.getFirstSelectedOption().getText();
	selectCountry.selectByIndex(23);
	System.out.println(option1);
	}
}
