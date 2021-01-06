package stepdef;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TheDemoSiteBDD {
	private static WebDriver driver;
	private String URL = "http://www.thedemosite.co.uk/";
	private String testName = "Testing";
	private String testPassword = "TESTPASS";

	@Before
	public static void init() {
		System.setProperty("webdriver.gecko.driver",
				"src/test/resources/drivers/geckodriver-v0.26.0-win64/geckodriver.exe");
		FirefoxOptions fOptions = new FirefoxOptions();
		fOptions.setHeadless(true);
		driver = new FirefoxDriver(fOptions);
		fOptions.addPreference("profile.default_content_setting_values.cookies", 2);
		fOptions.addPreference("network.cookie.cookieBehavior", 2);
		fOptions.addPreference("profile.block_third_party_cookies", true);
		driver.manage().window().setSize(new Dimension(1366, 768));
	}

	@After
	public static void tearDown() {
		driver.quit();
	}

	@When("^I navigate to the Add a User Page$")
	public void i_navigate_to_the_Add_a_User_Page() {
		driver.get(URL);
		assertEquals("FREE example PHP code and online MySQL database - example username password protected site",
				driver.getTitle());
	}

	@When("^Fill in the credentials for username and password$")
	public void fill_in_the_credentials_for_username_and_password() {
		driver.findElement(
				By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]"))
				.click();
		WebElement nameBar = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
		WebElement password = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
		WebElement submit = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
		nameBar.sendKeys(testName);
		password.sendKeys(testPassword);
		submit.click();
	}

	@Then("^I can sign in with the user credentials$")
	public void i_can_sign_in_with_the_user_credentials() throws Throwable {
		driver.findElement(
				By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"))
				.click();
		WebElement name = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
		WebElement password = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
		WebElement submit = driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
		name.sendKeys(testName);
		password.sendKeys(testPassword);
		submit.click();

		String result = driver
				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"))
				.getText();
		assertEquals("**Successful Login**", result);
	}
}
