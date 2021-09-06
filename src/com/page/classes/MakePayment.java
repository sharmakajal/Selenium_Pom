package com.page.classes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.setup.utilities.BaseUtils;
public class MakePayment {

	WebDriver driver;
	BaseUtils baseUtils;
	static WebDriverWait wait;
	String Moisturizers = "Moisturizers";
	String Sunscreen = "Sunscreens";
	String link1= "Buy moisturizers";
	String link2= "Buy sunscreens";
	String p1 ="Vassily Aloe Attack";
	String p2 ="Boris Almond and Honey";
	String p3 ="Akiba Amazing SPF-50";
	String p4 = "Vassily Brilliant SPF-30";

	/** 
	 * MakePayment() is a constructor.
	 * @param driver
	 */ 
	public MakePayment(WebDriver driver1){
		this.driver = driver1;
		PageFactory.initElements(driver, this);
		baseUtils = new BaseUtils(driver1);
	}

	//---------------------------------------Web Objects ---------------------------------------------------------	  
	@FindBy(how = How.XPATH, using = "//*[@id='weather']/span")
	private WebElement weather;
	public  WebElement getweather() {
		return weather;
	}

	@FindBy(how = How.XPATH, using = "//*[@id='weather']/span/sup")
	private WebElement celsius;
	public  WebElement getCelsius() {
		return celsius;
	}

	public WebElement buyProductLink(String options) {
		return	driver.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(),'"+options+"')]"));
	}

	public WebElement getXpathwithContainText(String value) {
		return	driver.findElement(By.xpath("//*[contains(text(),'"+value+"')]"));
	}
	public WebElement getProductAdd(String value) {
		return	driver.findElement(By.xpath("//p[contains(text(),'"+value+"')]/../button"));
	}

	public WebElement getProductWithText() {
		return	driver.findElement(By.xpath("//p[@class='font-weight-bold top-space-10']"));
	}
	public WebElement verifyDetails(String v) {
		return driver.findElement(By.xpath("//tr[1]/td[contains(text(),'"+v+"')]"));
	}

	@FindBy(how = How.XPATH, using ="//button[@onclick='goToCart()']")
	private WebElement goToCart;
	public  WebElement getGoToCart() {
		return goToCart;
	}
	@FindBy(how = How.XPATH, using ="//*[@id='total']")
	private WebElement totalAmt;
	public  WebElement getTotalAmt() {
		return totalAmt;
	}
	@FindBy(how = How.XPATH, using ="//button[@type='submit']")
	private WebElement submitBtn;
	public  WebElement getSubmitBtn() {
		return submitBtn;
	}

	@FindBy(how = How.XPATH, using ="//input[@placeholder='Email']")
	private WebElement emailField;
	public  WebElement getEmailField() {
		return emailField;
	}

	@FindBy(how = How.XPATH, using ="//input[@placeholder='Card number']")
	private WebElement cardField;
	public  WebElement getCardField() {
		return cardField;
	}

	@FindBy(how = How.XPATH, using ="//input[@placeholder='MM / YY']")
	private WebElement dateField;
	public  WebElement getDateField() {
		return dateField;
	}
	@FindBy(how = How.XPATH, using ="//input[@placeholder='ZIP Code']")
	private WebElement zipcodeField;
	public  WebElement getZipcodeField() {
		return zipcodeField;
	}


	@FindBy(how = How.XPATH, using ="//input[@placeholder='CVC']")
	private WebElement cvcField;
	public  WebElement getCvcField() {
		return cvcField;
	}
	@FindBy(how = How.XPATH, using ="//button[@class='Button-animationWrapper-child--primary Button']")
	private WebElement payBtn;
	public  WebElement getPayBtn() {
		return payBtn;
	}

	@FindBy(how = How.XPATH, using ="//*[@class='text-justify']")
	private WebElement successMsg;
	public  WebElement getSuccessMsg() {
		return successMsg;
	}

	@FindBy(how = How.XPATH, using ="//div/h2[contains(text(),'PAYMENT SUCCESS')]")
	private WebElement successheader;
	public  WebElement getSuccessheader() {
		return successheader;
	}
	@FindBy(how = How.XPATH, using ="//*[contains(text(),'Stripe.com')]")
	private WebElement popupHeader;
	public  WebElement getPopupHeader() {
		return popupHeader;
	}


	//**************************************************Methods************************************************	
	/**
	 * makePaymentValidationMethod()
	 * This is the main payment method which performs all the activities as mentioned in TC description
	 * 
	 */
	public void makePaymentValidationMethod() {
		try {
			System.out.println("Starting of the makePaymentValidationMethod()");
			getElementText(getweather(), "GetWeather Text");
			getElementText(getCelsius(), "GetTemperature Value");
			String tempvalue = getweather().getText();
			int value = Integer.parseInt(tempvalue);
			productsOnTemperature(value);
		}catch (Exception e) {
			System.out.println("Caught Exception" +e.getLocalizedMessage());
		}

	}

	/**
	 * getElementText(WebElement element,String objName)
	 * To verify the getElement Text based on Attribute or Values
	 * @param WebElement element
	 * @param String objName
	 * 
	 */	
	public String getElementText(WebElement element,String objName){
		String valueFromUI =null;
		try{
			wait = new WebDriverWait (driver,20);
			wait.until(ExpectedConditions.visibilityOf(element));
			if(element.isDisplayed()) {
				valueFromUI= element.getText();
				System.out.println("Value from Temperature is - " + valueFromUI);
			}
			if (valueFromUI.isEmpty()) {
					valueFromUI = element.getAttribute("value");
					System.out.println("Value from Temperature is - " + valueFromUI);
				}
				System.out.println("Executing Else BLock");
			System.out.println("Executed Successfully the try Block - getElementText()");
		}
		catch (Exception e) {
			System.out.println("Caught Exception" +e.getLocalizedMessage());
		}
		return valueFromUI;
	}
	/**
	 * productsOnTemperature()
	 * To get Product based on Temperature
	 * @param tempValue = Temperature values
	 */
	public void  productsOnTemperature(int tempValue) {
		try {
			System.out.println("Method to Choose Products based on TempValue");
			if(tempValue==0) {
				System.out.println("No product to choose, exist it");
			}else
			{
				if(tempValue<19) {
					System.out.println("Selecting the Moisturizers");
					//Method to choose Moisturizers
					Assert.assertTrue(getXpathwithContainText(Moisturizers).isDisplayed());
					chooseproductAndMakePayment(link1,Moisturizers, p1, p2);

				}
				else {
					if(tempValue>34) {
						System.out.println("Selecting the SunScreens");
						//method to choose SunScreen
						Assert.assertTrue(getXpathwithContainText(Sunscreen).isDisplayed());
						chooseproductAndMakePayment(link2, Sunscreen, p3, p4);
					}
					else {
						if(tempValue>19 && tempValue<34) {
							System.out.println("Do Nothing exit from Program");
						}
					}

				}
			}
		}catch (Exception e) {
			System.out.println("Caught Exception in productsOnTemperature()" +e.getLocalizedMessage());
		}
	}
	/**
	 * chooseproductAndMakePayment(String option,String Label,String productName1,String productName2)
	 * To add product on the chart, perform certain validations and make the payment, validate successmsg once done
	 * @param option - sunscreen or moisturizers
	 * @param label - text to verify
	 * @param productName1 - products to add to cart 
	 * @param productName2 - 2nd product to add to cart
	 */
	public void chooseproductAndMakePayment(String option,String Label,String productName1,String productName2) {
		try {
			System.out.println("Method to chooseproductAndMakePayment");
			//choose product and make payment
			Thread.sleep(5000);
			buyProductLink(option).click();
			if(getXpathwithContainText(Label).isDisplayed()) {
				Thread.sleep(2000);
				Assert.assertEquals(getProductWithText().getText(), productName1);
				getProductAdd(productName1).click();
				Assert.assertEquals(getProductWithText().getText(), productName2);
				getProductAdd(productName2).click();
				Thread.sleep(3000);
				getGoToCart().click();
				if(verifyDetails(productName1).isDisplayed()) {
					System.out.println("Value present in list");
				}
				if(verifyDetails(productName2).isDisplayed()) {
					System.out.println("Value present in list");
				}
				getPayBtn().click();
				Thread.sleep(10000);
				if(popupHeader.isDisplayed()) {
					System.out.println("Pay Page is displayed");
					getEmailField().sendKeys(baseUtils.InitProperties("email"));
					getCardField().sendKeys(baseUtils.InitProperties("number"));
					getCvcField().sendKeys(baseUtils.InitProperties("cvc"));
					getDateField().sendKeys(baseUtils.InitProperties("date"));
					getZipcodeField().sendKeys(baseUtils.InitProperties("zipcode"));
					getPayBtn().click();
				}
				wait= new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOf(getSuccessheader()));
				Assert.assertEquals(getSuccessMsg().getText(),"Your payment was successful. You should receive a follow-up call from our sales team.");
			}
		}	catch (Exception e) {
			System.out.println("Caught Exception in chooseproductAndMakePayment())" +e.getLocalizedMessage());
		}
	}
}