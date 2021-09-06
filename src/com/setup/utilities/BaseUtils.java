package com.setup.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import com.page.classes.MakePayment;
public class BaseUtils {

	public static WebDriver driver;
	private static MakePayment mp;
	public static String Browser;
	private static Properties config;
	private static String directory;

	/**
	 * BaseUtils() is a constructor
	 * @param driver1
	 */
	public BaseUtils(WebDriver driver1) {
		driver = driver1;
		PageFactory.initElements(driver, this); 
		initBaseUtils(driver);
	}
	/** 
	 * initBaseUtils() method contains set of instruction to Initialize the BaseUtils objects
	 * @return Nothing.
	 **/	
	public static void initBaseUtils(WebDriver driver2) {
		mp = new MakePayment(driver2);
	}
	/** 
	 * getDriver() is getter method of WebDriver object
	 *  @return WebDriver object.
	 **/
	public static WebDriver getDriver()
	{
		return driver;
	}
	/** 
	 * getmakepayment() is getter method of Actions object
	 * @return action object.
	 **/	
	public static MakePayment getmakepayment()	 {
		return mp; 
	}

	/**************************************************All Set Up Methods below **********************************

	/** 
	 * intializeDriver() method contains set of instruction to 
	 * Start the particular Browser and returns the driver  
	 * @throws  IOException 
	 * @param  String Browser
	 * @return 
	 * @return RemoteWebDriver
	 **/
	public static WebDriver setup() {
		try {
			Browser = InitProperties("browser.name");
			if(Browser.equalsIgnoreCase("IE"))
			{
				System.setProperty("webdriver.ie.driver",getPresentWorkingDir()+File.separator+"src"+File.separator+"com"+File.separator+"external"+File.separator+"IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
			else if(Browser.equalsIgnoreCase("Firefox"))
			{
				System.setProperty("webdriver.gecko.driver",getPresentWorkingDir()+File.separator+"src"+File.separator+"com"+File.separator+"external"+File.separator+"geckodriver.exe");
				driver = new FirefoxDriver();
			} 
			else if(Browser.equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver",getPresentWorkingDir()+File.separator+"src"+File.separator+"com"+File.separator+"external"+File.separator+"chromedriver.exe");
				driver = new ChromeDriver();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Executed the Browser Setup Block");
		} 
		return driver;
	}
	/** 
	 * InitProperties() method to read key, values from the configuration files.
	 * @param toread = Property name to read
	 * @return value of the string to return
	 **/
	public static String InitProperties(String toread) {
		config = readProperties("configuration.properties",File.separator+"Configs");
		String value = config.getProperty(toread);
		return value;
	}
	/** 
	 * getPresentWorkingDir() method to get the Working Directory Path
	 * @param path = User directory path
	 * @return path
	 **/
	public static String getPresentWorkingDir()	throws IOException	{
		String path;
		path = System.getProperty("user.dir");
		return path;
	}
	/** 
	 * getPresentWorkingDir() method to get the Working Directory Path
	 * @param path = User directory path
	 * @return path
	 **/
	public static  Properties readProperties(String Filename, String Folder){
		Properties props = new Properties();
		try {
			File config_file = new File(Folder + File.separator + Filename);
			directory = getPresentWorkingDir();
			config_file = new File(directory + File.separator + Folder + File.separator + Filename);
			if (config_file == null) {
				System.out.println("Couldn't find " + Filename + ", Due to this the rest of the tests will fail");
			}
			props.load(new FileInputStream(config_file));
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return props;
	}

}
