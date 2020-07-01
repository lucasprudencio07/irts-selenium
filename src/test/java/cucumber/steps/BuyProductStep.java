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

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lprudencio\\Downloads\\WebDriver\\chromedriver83.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\lucas\\Documents\\Drivers\\chromedriver83.exe");

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

    //------------------------------------------------------------------------------------------------------

    @When("I am logged in")
    public void i_am_logged_in() {
        
    }


    @When("I selected a product at index {int}")
    public void i_selected_a_product_at_index(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @When("the product name on the home screen is {string}")
    public void the_product_name_on_the_home_screen_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @When("the product price on the home screen is {string}")
    public void the_product_price_on_the_home_screen_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @When("I added the product on the shopping cart with a {string} size with a {string} color and {int} itens")
    public void i_added_the_product_on_the_shopping_cart_with_a_size_with_a_color_and_itens(String string, String string2, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Then("the product will show on the confirmation page as {string} costing {string} at a {string} size with a {string} color and {int} itens")
    public void the_product_will_show_on_the_confirmation_page_as_costing_at_a_size_with_a_color_and_itens(String string, String string2, String string3, String string4, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @After
    public static void tearDown() {
        chromeDriver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        chromeDriver.quit();
    }

}
