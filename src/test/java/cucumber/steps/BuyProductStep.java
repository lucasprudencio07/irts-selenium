package cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.HomePage;
import selenium.pages.ModalPage;
import selenium.pages.ProductPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BuyProductStep {

    private static WebDriver chromeDriver;

    private ProductPage productPage;
    private ModalPage modalPage;

    private String productName_home;
    private String productPrice_home;
    private String productPrice_tshirt;
    private String productName_tshirt;

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

        homepage.clickSignInButton().fillLoginForm("lucas@teste.com", "123456");

        String homepageName = homepage.getLoginNameAtHeader();

        assertThat(homepageName, is(equalTo("Lucas Santos")));

        homepage.loadHomePage();
    }


    @When("I selected a product at index {int}")
    public void i_selected_a_product_at_index(Integer index) {

        homepage.getNumberOfProductsOnTheHomepage();

        productName_home = homepage.getTShirtNameHome(index).toLowerCase();
        productPrice_home = homepage.getTShirtPriceHome(index);

        productPage = homepage.clickProduct(index);

        productName_tshirt = productPage.getTShirtNameProductPage().toLowerCase();
        productPrice_tshirt = productPage.getTShirtPriceProductPage();

        assertThat(productName_tshirt, is(equalTo(productName_home)));
        assertThat(productPrice_tshirt, is(equalTo(productPrice_home)));
    }


    @When("the product name on the home screen and on the product page is {string}")
    public void the_product_name_on_the_home_screen_and_on_the_product_page_is(String shirtName) {
        assertThat(productName_home, is(equalTo(shirtName.toLowerCase())));
        assertThat(productName_tshirt, is(equalTo(shirtName.toLowerCase())));
    }


    @When("the product price on the home screen and on the product page is {string}")
    public void the_product_price_on_the_home_screen_and_on_the_product_page_is(String shirtPrice) {
        assertThat(productPrice_home, is(equalTo(shirtPrice.toLowerCase())));
        assertThat(productPrice_tshirt, is(equalTo(shirtPrice.toLowerCase())));
    }


    @When("I added the product on the shopping cart with a {string} size with a {string} color and {int} itens")
    public void i_added_the_product_on_the_shopping_cart_with_a_size_with_a_color_and_itens(String tShirtSize, String tShirtColor, Integer quantity) {

        productPage.chooseSize(tShirtSize);

        if (!tShirtColor.equals("N/A")) productPage.chooseColor(tShirtColor);

        productPage.chooseQuantity(quantity.toString());

        modalPage = productPage.addToShoppingCart();

        assertThat(modalPage.getSelectedSizeModel(), is(equalTo(tShirtSize)));
        if (!modalPage.getSelectedColorModel().equals("N/A")) assertThat(modalPage.getSelectedColorModel(), is(equalTo(tShirtColor)));
        assertThat(modalPage.getSelectedQuantityModel(), is(equalTo(quantity.toString())));

    }


    @Then("the product will show on the confirmation page as {string} costing {string} at a {string} size with a {string} color and {int} itens")
    public void the_product_will_show_on_the_confirmation_page_as_costing_at_a_size_with_a_color_and_itens(String tShirtNameModal, String tshirtPriceModal,
                                                                                                           String tshirtSizeModal, String tshirtColorModal,
                                                                                                           Integer tShirtQuantityModal) {

        Double tShirtSubtotalPrice = Double.parseDouble(tshirtPriceModal.replace("$", "")) * tShirtQuantityModal;

        assertThat(modalPage.getSelectedProductNameModel().toLowerCase(), is(equalTo(tShirtNameModal.toLowerCase())));
        assertThat(modalPage.getSelectedSizeModel(), is(equalTo(tshirtSizeModal)));
        if (!modalPage.getSelectedColorModel().equals("N/A")) assertThat(modalPage.getSelectedColorModel(), is(equalTo(tshirtColorModal)));
        assertThat(modalPage.getSelectedQuantityModel(), is(equalTo(tShirtQuantityModal.toString())));
        assertThat(modalPage.getSubtotalValueModel(), is(equalTo(tShirtSubtotalPrice)));
        assertThat(modalPage.getMessageProductAddedModel(), is(equalTo("Product successfully added to your shopping cart")));

    }


    @After (order = 1)
    public void takeScreenshot(Scenario scenario) {

        File sourceFile = ((TakesScreenshot)chromeDriver).getScreenshotAs(OutputType.FILE);
        String fileName = "screenshots/" + scenario.getName() + "_" + scenario.getStatus().toString().toLowerCase() + "_" + scenario.getLine() + ".png";

        System.out.println("Taking a screenshot and saving as: " + fileName);

        try {
            FileUtils.copyFile(sourceFile, new File(fileName));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());

        }

    }


    @After (order = 0)
    public static void tearDown() {
        chromeDriver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        chromeDriver.quit();
    }

}
