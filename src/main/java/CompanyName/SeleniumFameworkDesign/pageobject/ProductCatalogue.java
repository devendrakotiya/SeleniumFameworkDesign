package CompanyName.SeleniumFameworkDesign.pageobject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ComapanyName.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
    
	public ProductCatalogue(WebDriver driver)
	{
		//initializing we can use constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	By toastContainer = By.cssSelector("#toast-container");
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement item =  getProductList().stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		item.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		return item;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement item = getProductByName(productName);
		item.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementTodisappear(spinner);

	}
	

}
