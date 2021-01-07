package stepdef;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShoppingWebsite {
	private static WebDriver driver;
	private String URL = "http://automationpractice.com/index.php";
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

	@Given("^I am on the correct website$")
	public void i_am_on_the_correct_website() {
		driver.get(URL);
		assertEquals("My Store", driver.getTitle());
	}

	@When("^I search for a skirt$")
	public void i_search_for_a_skirt() {
		WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
		searchBar.sendKeys("Printed Summer Dress");
		searchBar.submit();
		new WebDriverWait(driver, 3)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"center_column\"]/h1")));
	}

	@Then("^I will receive a list of items that match my description$")
	public void i_will_receive_a_list_of_items_that_match_my_description() {
		WebElement product = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div/div[2]/h5/a"));
		assertEquals(product.getText(), "Printed Summer Dress");

	}

	@Given("^I have an item in my basket$")
	public void i_have_an_item_in_my_basket() {
	}

	@When("^I proceed to checkout$")
	public void i_proceed_to_checkout() {
	}

	@When("^fill in my customer details$")
	public void fill_in_my_customer_details() {
	}

	@Then("^I should be able to purchase the item$")
	public void i_should_be_able_to_purchase_the_item() {
	}
}
