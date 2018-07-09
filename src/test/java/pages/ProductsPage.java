package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class ProductsPage {
	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table[@class='ProductsTable']/tbody/tr/td[1]")
	public List<WebElement> productNames;
	
	@FindBy(xpath="//table[@class='ProductsTable']/tbody/tr")
	public List<WebElement> productRows;
	
	
}
