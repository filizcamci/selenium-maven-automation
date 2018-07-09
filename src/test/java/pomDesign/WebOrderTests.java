package pomDesign;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.OrderPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;


public class WebOrderTests {
	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrdersPage;
	ProductsPage productsPage;
	OrderPage orderPage;
	String userId="Tester";
	String psw="test";
	Select select;
	

	@BeforeClass
	public void setUp() {
		System.out.println("setting up WebDriver before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@BeforeMethod
	public void positiveLoginTest() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage=new WebOrdersLoginPage(driver);
	}
	@Test(priority=1)
	public void labelsVerification() {
		assertEquals(driver.getTitle(),"Web Orders Login","Login page is not displayed");
		//loginPage.userName.sendKeys("Tester");
		//loginPage.password.sendKeys("test");
		//loginPage.loginButton.click();
		loginPage.login(userId, psw);
		allOrdersPage=new AllOrdersPage(driver);
		
		assertTrue(allOrdersPage.webOrders.isDisplayed(),"Web Orders is not displayed");
		assertTrue(allOrdersPage.allOrders.isDisplayed(),"List of all orders is not displayed");
		assertEquals(allOrdersPage.welcomeMessage.getText().replace(" | Logout", ""),"Welcome, "+userId+"!");
		assertTrue(allOrdersPage.allOrders.isDisplayed(),"view all orders is not displayed");
		assertTrue(allOrdersPage.orderTab.isDisplayed(),"order is not displayed");
		
	}
	
	@Test(description="verify default products and prices",priority=2)
	public void availableProductsTest() {
		assertEquals(driver.getTitle(),"Web Orders Login","Login page is not displayed");
		loginPage.login(userId, psw);
		allOrdersPage=new AllOrdersPage(driver);
		allOrdersPage.products.click();
		productsPage=new ProductsPage(driver);
		List<String> expectedProducts=Arrays.asList("MyMoney","FamilyAlbum","ScreenSaver");
		List<String> actualProducts=new ArrayList<>();
		for(WebElement product: productsPage.productNames) {
			actualProducts.add(product.getText());
		}
		assertEquals(actualProducts,expectedProducts);
		for(WebElement row:productsPage.productRows) {
			System.out.println(row.getText());
			String[] prodData=row.getText().split(" ");
			switch(prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1],"$100");
				assertEquals(prodData[2],"8%");
				break;
			case "FamilyAlbum":
				assertEquals(prodData[1],"$80");
				assertEquals(prodData[2],"15%");
				break;
			case "ScreenSaver":
				assertEquals(prodData[1],"$20");
				assertEquals(prodData[2],"10%");
				break;
			}
			
		}
		
	}
	
	@Test(priority=3)
	public void orderTest() {
		loginPage.login(userId, psw);
		loginPage.order.click();
		orderPage=new OrderPage(driver);
		select=new Select(orderPage.product);
		select.selectByValue("FamilyAlbum");
		orderPage.quantity.clear();
		orderPage.quantity.sendKeys("2");
		orderPage.calculate.click();
		String name="Evelyn Smith";
		orderPage.name.sendKeys(name);
		orderPage.street.sendKeys("1234 Bridge St.");
		orderPage.city.sendKeys("Ann Arbor");
		orderPage.state.sendKeys("Michigan");
		orderPage.zip.sendKeys("42345");
		orderPage.visa.click();
		orderPage.cardNumber.sendKeys("1234567891011");
		orderPage.expDate.sendKeys("11/22");
		orderPage.processButton.click();
		assertTrue(driver.findElement(By.tagName("strong")).isDisplayed(),"New order has been successfully added. is not displayed");
		orderPage.viewOrders.click();
		allOrdersPage=new AllOrdersPage(driver);
		System.out.println(allOrdersPage.tableName.getText());
		assertEquals(allOrdersPage.tableName.getText(),name);
		
		
	}
	
	//@AfterMethod
	public void logout() {
		allOrdersPage.logout();
	}
	//@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
