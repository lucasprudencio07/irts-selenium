package cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.HomePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class SeeProductStep {

    private static WebDriver chromeDriver;
    private final HomePage homepage = new HomePage(chromeDriver);


    @Before
    public static void setUp() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lprudencio\\Downloads\\WebDriver\\chromedriver83.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lucas\\Documents\\Drivers\\chromedriver83.exe");

        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        chromeDriver.manage().window().maximize();
    }


    @Given("I am in the home screen")
    public void i_am_in_the_home_screen() {
        homepage.loadHomePage();
        assertThat(homepage.getPageName(), is(equalTo("Loja de Teste")));
    }


    @When("I am not logged in")
    public void i_am_not_logged_in() {
        assertThat(homepage.isUserLoggedIn(), is(false));
    }


    @Then("I can see all the {int} products available to buy")
    public void i_can_see_all_the_products_available_to_buy(Integer numberOfProducts) {
        assertThat(homepage.getNumberOfProductsOnTheHomepage(), is(equalTo(numberOfProducts)));
    }


    @Then("the shopping cart must be empty")
    public void the_shopping_cart_must_be_empty() {
        assertThat(homepage.getNumberOfProductsAtShoppingCart(), is(equalTo(0)));
    }


    @After
    public static void tearDown() {
        chromeDriver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        chromeDriver.quit();
    }

}
