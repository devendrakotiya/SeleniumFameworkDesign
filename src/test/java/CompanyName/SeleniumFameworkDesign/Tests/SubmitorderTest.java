package CompanyName.SeleniumFameworkDesign.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CompanyName.SeleniumFameworkDesign.pageobject.CartPage;
import CompanyName.SeleniumFameworkDesign.pageobject.CheckoutPage;
import CompanyName.SeleniumFameworkDesign.pageobject.ConfirmationPage;
import CompanyName.SeleniumFameworkDesign.pageobject.LandingPage;
import CompanyName.SeleniumFameworkDesign.pageobject.ProductCatalogue;
import CompanyName.TestComponents.BaseTest;
import io.opentelemetry.sdk.metrics.internal.state.ObjectPool;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class SubmitorderTest extends BaseTest {

	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException
	{
	
		String prod = input.get("product");
		// we can remove landing page line also using @Beforemethod
//		LandingPage landingPage = launchApplication();
		// now this landingpage variable we can define globally in base test class
		landingPage.loginApplication(input.get("email"), input.get("password"));
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(prod);
		productCatalogue.goToCart();
		CartPage cartPage = new CartPage(driver);
		Boolean Match = cartPage.VerifyProductDisplay(prod);
		Assert.assertTrue(Match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMSG = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMSG.equalsIgnoreCase("Thankyou for the order."));
	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\ComapanyName\\data\\PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
	
//	HashMap<String, String> map = new HashMap<String, String> ();
//	map.put("email", "edinrose@gmail.com");
//	map.put("password", "Edinrose@1");
//	map.put("product", "IPHONE 13 PRO");
//	
//	HashMap<String, String> map1 = new HashMap<String, String> ();
//	map.put("email", "shetty@gmail.com");
//	map.put("password", "sHETTY@46t877");
//	map.put("product", "ZARA COAT 3");
	
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object [][] {{"edinrose@gmail.com", "Edinrose@1", "IPHONE 13 PRO"},{"shetty@gmail.com", "sHETTY@46t877", "ZARA COAT 3"}};
//	}
	
	
}
