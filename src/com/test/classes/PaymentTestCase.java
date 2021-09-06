package com.test.classes;
/* Author Name		: 	Kajal Sharma
 * Creation Date	: 	13-07-2020
 * Epic/User story	: 	Interview Test Sample
 * Test case name	:   Complete flow of Product from Selecting to Purchasing it.
 * Prerequisite 	:   Application Url navigation.
 * Description		:   Validating complete process of any product from selection till purchasing it.
 * Tests Covered    :	a) visit ://weathershopper.pythonanywhere.com/
						b) Get the temperature
						c) Based on temperature choose to buy sunscreen or moisturizer
						d) If you choose sunscreen, then read Instructions and then add product accordingly
						e) If you choose moisturizer, then read Instructions and then add product accordingly
						f) Verify the cart
						g) Make payment
						h) verify successMessage.
 * 						
 * */
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import com.page.classes.MakePayment;
import com.setup.utilities.*;
import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class PaymentTestCase {
	protected static WebDriver driver;
	public static String applicationURL;
	public static String wait;
	MakePayment mp;

	@BeforeSuite
	public void beforeSuite() throws IOException {	// creating reporting folder and files before test suit execution
		driver = BaseUtils.setup();
		new BaseUtils(driver);
	}
	@BeforeClass(alwaysRun=true)
	public void BeforeEachClass() throws Throwable
	{	
		mp = BaseUtils.getmakepayment();
		applicationURL=BaseUtils.InitProperties("url");
	}

	@BeforeMethod(alwaysRun=true)
	public void BeforeEachMethod() throws Exception{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(applicationURL);
		System.out.println("Title of Page:" + driver.getTitle());
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	@Test
	public void TS_PaymentMethod() {
		try {
			System.out.println("Executing the TS_PaymentMethod Test Case");
			mp.makePaymentValidationMethod();
			System.out.println("TC's executed successfully");
		}catch(Exception p) {
			System.out.println("Exception caught in TS_PaymentMethod()" +p.getLocalizedMessage());
		}
	}
	@AfterSuite(alwaysRun = true)
	public void afterClassMetod() throws InterruptedException,IOException, AWTException {
		driver.quit();	
	}


}