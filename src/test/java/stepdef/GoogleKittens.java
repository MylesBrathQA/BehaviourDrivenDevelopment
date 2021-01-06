package stepdef;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoogleKittens {
	private static WebDriver driver;
	private String URL = "https://www.google.com";

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

	@Given("^I can access google$")
	public void i_can_access_google() {
		driver.get(URL);
		if (driver.getTitle().equals("Google")) {
			System.out.println("I can open google");
			System.out.println(driver.getCurrentUrl());
		} else {
			System.out.println("Google didn't open");
		}
	}

	@When("^I search for kittens$")
	public void i_search_for_kittens() {
		WebElement searchBar = driver.findElement(By.xpath("//input[@name = 'q']"));
		searchBar.sendKeys("Kittens");
	}

	@Then("^Google will return kittens$")
	public void google_will_return_kittens() {
		driver.findElement(By.xpath("//input[@name = 'q']")).clear();
		driver.getPageSource().contains("kittens");
	}
}
