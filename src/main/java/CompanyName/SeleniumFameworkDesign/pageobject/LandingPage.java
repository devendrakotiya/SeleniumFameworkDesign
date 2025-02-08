package CompanyName.SeleniumFameworkDesign.pageobject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ComapanyName.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
    
	public LandingPage(WebDriver driver)
	{
		//initializing we can use constructor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
//	WebElement userEmail = driver.findElement(By.id("userEmail")); 
//	WebElement password = driver.findElement(By.id("userPassword")); 
	
	// instead of writing above line we have alternate method called PAGE FACTORY
	//Page Factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*=flyInOut]")
	WebElement errorMessage;
	
	
	// Action method
	public void loginApplication(String email, String Password)
	{
		userEmail.sendKeys(email);
		password.sendKeys(Password);
		submit.click();
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
