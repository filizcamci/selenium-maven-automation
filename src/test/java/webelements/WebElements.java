package webelements;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
	WebDriver driver;
	Faker faker = new Faker();

	@BeforeClass
	public void setUp() {
		System.out.println("setting up WebDriver before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	// public void myLinks() {
	// driver.get("https://github.com");
	// // Let us find all the links in github webpage
	// // List<WebElement> links=driver.findElements(By.tagName("a"));
	// List<WebElement> links = driver.findElements(By.xpath("//a"));
	// int numberOfLinks = links.size();
	// System.out.println("number of the links: " + numberOfLinks);
	// for (int i = 0; i < links.size(); i++) {
	// System.out.println(links.get(i).getText());
	// // add each link text into a list of strings
	// }
	// List<String> linkTexts = new ArrayList();
	// for (WebElement link : links) {
	// if (!link.getText().isEmpty()) {
	// linkTexts.add(link.getText());
	// }
	// }
	// System.out.println(linkTexts);
	//
	// }

	/*
	 * navigate to
	 * https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/
	 * eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg Find all input boxes and assign
	 * to List of webelements -> 2 Find all drop down boxes and assign to another
	 * List of webelements -> 3 Find all check boxes and assign to another List of
	 * webelements -> 9 Find all radio boxes and assign to another List of
	 * webelements -> 9 Find all buttons and assign to another List of webelements
	 * -> 1 assert each one’s count
	 */
	@Test
	public void SeleniumForm() throws InterruptedException {
		driver.get(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		List<WebElement> inboxes = driver.findElements(By.xpath("//input[@type='text']"));
		assertEquals(inboxes.size(), 2);
		List<WebElement> dropdowns = driver.findElements(By.tagName("select"));
		assertEquals(dropdowns.size(), 3);
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		assertEquals(checkboxes.size(), 9);
		List<WebElement> radios = driver.findElements(By.xpath("//input[@type='radio']"));
		assertEquals(radios.size(), 9);
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		assertEquals(buttons.size(), 1, "message shows in case of failure");
		/*
		 * Homework: Loop through each input box and enter random names 
		 * Loop through each dropDown and randomly select by index 
		 * Loop through each checkBoxes and select each one 
		 * Loop through each radioButton and click one by one by waiting
		 * one second intervals click all buttons
		 */
		for (int i = 0; i < inboxes.size(); i++) {
			String name = faker.name().fullName();
			inboxes.get(i).sendKeys(name);
		}

		for (int i = 0; i < dropdowns.size(); i++) {
			int index = faker.number().numberBetween(0, 4);
			Select select = new Select(dropdowns.get(i));
			select.selectByIndex(index);

		}
		for (int i = 0; i < checkboxes.size(); i++) {

			checkboxes.get(i).click();

		}
		for (int i = 0; i < radios.size(); i++) {

			radios.get(i).click();
			Thread.sleep(1000);

		}
	}
}
