package stepdef;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PassionateTeaTest {
	private static WebDriver driver;
	private String URL = "thedemosite.co.uk/";
	private static Actions action;

	@Before
	public static void init() {
		System.setProperty("webdriver.gecko.driver",
				"src/test/resources/drivers/geckodriver-v0.26.0-win64/geckodriver.exe");
		FirefoxOptions fOptions = new FirefoxOptions();
		fOptions.setHeadless(false);
		driver = new FirefoxDriver(fOptions);
		fOptions.addPreference("profile.default_content_setting_values.cookies", 2);
		fOptions.addPreference("network.cookie.cookieBehavior", 2);
		fOptions.addPreference("profile.block_third_party_cookies", true);
		driver.manage().window().setSize(new Dimension(1366, 768));
		action = new Actions(driver);
	}

	@After
	public static void tearDown() {
		driver.quit();
	}

	@Given("^The correct web address$")
	public void the_correct_web_address() throws InterruptedException {
		driver.get(URL);
		System.out.println(driver.getCurrentUrl());
		assertEquals("Welcome", driver.getTitle());
	}

	@When("^I I navigate to the 'Menu' Page$")
	public void i_navigate_to_the_Menu_Page() {
		WebElement menuButton = driver
				.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]/a"));
		menuButton.click();
		assertEquals("Menu", driver.getTitle());

	}

	@Then("^I can browse a list of available items$")
	public void i_can_browse_a_list_of_available_items() {
		boolean browse = driver.getPageSource().contains("Tea");
		assertEquals(true, browse);
	}

	@Given("^Navigate to the correct web address$")
	public void navigate_to_the_correct_web_address() {
		driver.get(URL);
		assertEquals("Welcome", driver.getTitle());

	}

	// Can't find the checkout button on webpage holding up testing
	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() {
		driver.get(URL);
		WebElement menuButton = driver
				.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]/a"));
		WebElement greenTea = driver
				.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000451955160\"]/div"));
		action.moveToElement(menuButton).click().moveToElement(greenTea).click().build().perform();
		assertEquals("Check Out", driver.getTitle());

	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() {
		WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		WebElement name = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		WebElement address = driver.findElement(By.xpath("//*[@id=\"address\"]"));
		WebElement card = driver.findElement(By.xpath("//*[@id=\"card_type\"]"));
		WebElement cardNumber = driver.findElement(By.xpath("//*[@id=\"card_number\"]"));
		WebElement cardName = driver.findElement(By.xpath("//*[@id=\"cardholder_name\"]"));
		WebElement verifCode = driver.findElement(By.xpath("//*[@id=\"verification_code\"]"));
		WebElement placeOrder = driver.findElement(
				By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000452010925\"]/div/div/form/div/a"));
		email.sendKeys("test@selenium.com");
		name.sendKeys("test");
		address.sendKeys("123 Fake Road \n Fake City \nFK3 4BC");
		action.moveToElement(card).click().moveByOffset(0, 40).click().build().perform();
		cardNumber.sendKeys("12345");
		cardName.sendKeys("test");
		verifCode.sendKeys("345");
		placeOrder.click();
		assertEquals("Menu", driver.getTitle());
	}
}
