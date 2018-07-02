package WebTables;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadWebTables {
	String url = "file:///C:/Users/filiz/eclipse-workspace/selenium-maven-automation/src/test/java/WebTables/webtable.html";
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
	public void ReadScores() {
		driver.get(url);
		// Read whole webtable data
		WebElement table = driver.findElement(By.tagName("table"));
		//System.out.println(table.getText());

		// Find out how many rows in the table

		List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr"));
		//System.out.println("there are " + tableRows.size() + " rows in the table");
		
		for (WebElement each : tableRows) {
			//System.out.println(each.getText());
		}
		List<WebElement> headers = driver.findElements(By.xpath("//table[@id='worldcup']/thead/tr/th"));
		List<String> expectedHeaders = Arrays.asList("Team1", "Score", "Team2");
		List<String> actualHeaders = new ArrayList();
		;
		for (WebElement h : headers) {
			actualHeaders.add(h.getText());
			// System.out.println(h.getText());
		}
		// assertEquals(actualHeaders,expectedHeaders);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualHeaders, expectedHeaders);
		
		//print out Egypt from the table
		String e=driver.findElement(By.xpath("//table[@id='worldcup']/tbody/tr[3]/td[3]")).getText();
		//System.out.println(e);
		softAssert.assertEquals(e,"Egypt");
		
		//loop through all table and print out all data
		int rows=driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr")).size();
		int coloums=driver.findElements(By.xpath("//table[@id='worldcup']/thead/tr/th")).size();
		for(int i=1;i<=rows; i++) {
			for(int j=1; j<=coloums; j++) {
				String data=driver.findElement(By.xpath("//table[@id='worldcup']/tbody/tr["+i+"]/td["+j+"]")).getText();
				System.out.print(data+"   \t");
			}
			System.out.println();
		}
		softAssert.assertAll();
	}
		
		
		//challenge: 
	@Test
	public void applicantsData() {
		driver.get("https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8");
		printTableData("reportTab");
	}
	
		public void printTableData(String id) {
			int rowsCount = driver.findElements(By.xpath("//table[@id='"+id+"']/tbody/tr")).size();
			int colsCount = driver.findElements(By.xpath("//table[@id='"+id+"']/thead/tr/th")).size();
			
			System.out.println("===============");
			
			for(int rowNum = 1; rowNum <= rowsCount; rowNum++) {
				for(int col = 1; col <= colsCount; col++) {
					String xpath = "//table[@id='"+id+"']/tbody/tr["+rowNum+"]/td["+col+"]";
					String tdData = driver.findElement(By.xpath(xpath)).getText();
					System.out.print(tdData +"--");
				}
				System.out.println();
			}
		}
	
		
		
		
		
	

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
