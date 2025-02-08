package CompanyName.SeleniumFameworkDesign.Tests;

import java.io.IOException;

import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;
import org.testng.IRetryAnalyzer;
import com.sun.net.httpserver.Authenticator.Retry;

import CompanyName.TestComponents.BaseTest;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ErrorValidations extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{

		landingPage.loginApplication("edijrose@gmail.com", "edinrose@1");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test
	public void submitOrder() throws IOException
	{
	
		String prod = "IPHONE 13 PRO";

		landingPage.loginApplication("edijrose@gmail.com", "edinrose@1");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}

}
