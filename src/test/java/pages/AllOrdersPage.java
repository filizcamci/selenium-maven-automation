package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllOrdersPage {
	public AllOrdersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[.='Web Orders']")
	public WebElement webOrders;
	
	@FindBy(css=".login_info")//class=login info
	public WebElement welcomeMessage;
	
	@FindBy(xpath="//h2[contains(.,'List of All Orders')]")//xpath=	//*[contains(text(),'List of All Orders')]    //h2[.='List of All Orders']
	public WebElement allOrders;
	
	@FindBy(linkText="View all orders")
	public WebElement orders;
	
	@FindBy(linkText="View all products")
	public WebElement products;
	
	@FindBy(linkText="Order")
	public WebElement orderTab;
	
	@FindBy(id="ctl00_logout")
	public WebElement logoutLink;
	
	@FindBy(xpath="//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[2]")
	public WebElement tableName;
	
	public void logout() {
		logoutLink.click();
	}
	
	
	
}
