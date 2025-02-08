package CompanyName.SeleniumFameworkDesign.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CompanyName.SeleniumFameworkDesign.pageobject.LandingPage;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class StandAloneTest {

	private static final Object TRUE = null;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prod = "IPHONE 13 PRO";
		//edinrose@gmail.com   Edinrose@1
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("edinrose@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Edinrose@1");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
//		Java stream to iterate this products list
		WebElement item =  products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
		item.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
//		explicit wait implementation
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(prod));
		Assert.assertEquals(TRUE, match);
//		CHECKOUT CLICK IS NOT WORKING
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		Drop down handling
		Actions a = new Actions(driver);
		
		a.sendKeys(driver.findElement(By.className("[placeholder='Select Country']")),"india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmationMSG = driver.findElement(By.cssSelector(".hero-primary")).getText() ;
		Assert.assertTrue(confirmationMSG.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		
		
	}

}
