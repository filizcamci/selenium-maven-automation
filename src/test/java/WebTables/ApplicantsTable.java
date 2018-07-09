package WebTables;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ApplicantsTable {
	WebDriver driver;
	Map<String, String> applicants;

	@BeforeClass
	public void setUp() {
		System.out.println("setting up WebDriver before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void applicantsMap() throws InterruptedException {
		driver.get(
				"https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8");
		applicants = new HashMap();
		
		String totalString = driver.findElement(By.xpath("//span[@id='total']")).getText();
		int total = Integer.parseInt(totalString);
		
		if(total<10) {
			List<WebElement> ids = driver.findElements(By.xpath("//tbody//td[1]"));
			List<WebElement> fullnames = driver.findElements(By.xpath("//tbody//td[2]"));
			Thread.sleep(1000);
			List<WebElement> emails = driver.findElements(By.xpath("//tbody//td[3]"));
			Thread.sleep(1000);
			List<WebElement> phones = driver.findElements(By.xpath("//tbody//td[4]"));
			Thread.sleep(1000);
			List<WebElement> salaries = driver.findElements(By.xpath("//tbody//td[5]"));
			for (int i = 0; i < ids.size(); i++) {
				String value = fullnames.get(i).getText() + emails.get(i).getText() + phones.get(i).getText()
						+ salaries.get(i).getText();
				applicants.put(ids.get(i).getText(), value);

			}
		}
		
		
		
		
		else {
			Select select = new Select(driver.findElement(By.xpath("//select[@id='recPerPage']")));
			select.selectByVisibleText("20");
			String selected = driver.findElement(By.xpath("//select[@id='recPerPage']/option[@selected='selected']")).getText();
			int selectedint = Integer.parseInt(selected);
			// System.out.println(selectedint);

			int pages = (int)(Math.ceil(total / selectedint))+1;
			System.out.println("number of pages: "+pages);
		
		for (int j = 1; j <= pages; j++) {

			Thread.sleep(1000);
			List<WebElement> ids = driver.findElements(By.xpath("//tbody//td[1]"));
			// System.out.println("page: "+j+":"+ids.size());
			//Thread.sleep(2000);
			// System.out.println("ids: ");
			for (WebElement id : ids) {
				//System.out.println(id.getText());
			}
			Thread.sleep(1000);
			List<WebElement> fullnames = driver.findElements(By.xpath("//tbody//td[2]"));
			// System.out.println(fullnames.size());
			//Thread.sleep(2000);
			for (WebElement fullname : fullnames) {
				// System.out.print(fullname.getText());
				// System.out.println();
			}
			Thread.sleep(1000);
			List<WebElement> emails = driver.findElements(By.xpath("//tbody//td[3]"));
			// System.out.println(emails.size());
			//Thread.sleep(2000);
			Thread.sleep(1000);
			List<WebElement> phones = driver.findElements(By.xpath("//tbody//td[4]"));
			// System.out.println(phones.size());
			//Thread.sleep(2000);
			Thread.sleep(1000);
			List<WebElement> salaries = driver.findElements(By.xpath("//tbody//td[5]"));
			// System.out.println(salaries.size());
			//Thread.sleep(2000);
			for (int i = 0; i < ids.size(); i++) {
				String value = fullnames.get(i).getText() +" - "+ emails.get(i).getText() +" - "+ phones.get(i).getText()
						+" - "+ salaries.get(i).getText();
				applicants.put(ids.get(i).getText(), value);

			}
			// next page
			driver.findElement(By.xpath("//span[@id='showNext']/a")).click();

		}
		}
		System.out.println("total: "+applicants.size());
		//System.out.println(applicants);
		System.out.println("applicants table:");
		
		Set<Entry<String,String>> applicantsView=applicants.entrySet();
		for(Entry<String,String> applicant: applicantsView) {
			System.out.println(applicant.getKey()+" : "+applicant.getValue());
		}
		

		assertEquals(applicants.size(), total);
		
	}

	 @AfterClass
	 public void tearDown() throws InterruptedException {
	 Thread.sleep(2000);
	 driver.close();
	 }

}
