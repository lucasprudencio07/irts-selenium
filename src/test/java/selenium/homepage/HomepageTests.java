package selenium.homepage;

import org.junit.jupiter.api.Test;
import pages.ShoppingCartPage;
import selenium.base.BaseTests;
import selenium.pages.ModalPage;
import selenium.pages.ProductPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HomepageTests extends BaseTests {

    private final String productSize = "M";
    private final String productColor = "Black";
    private final String productQuantity = "2";
    private final String productQuantityAllTests = "4";
    private final Integer productIndex = 0;
    private final Double productPriceOne = 19.12;
    private final Double productPriceTotal = 38.24;
    private final Double productPriceAbsoluteMaximum = 76.48;

    private ModalPage modalPage;
    private ProductPage productPage;
    private ShoppingCartPage shoppingCartPage;

    /*
    um disclaimer é que o "assertThat" recebe primeiro o valor atual e depois o esperado,
    já o "assertEquals" do Junit recebe primeiro o valor esperado e depois o atual.
    */
    @Test
    public void testCountProducts() {

        loadInitialPage();

        assertThat(homepage.countProducts(), is(equalTo(8)));
//        assertEquals( 8, selenium.homepage.countProducts());
    }


    @Test
    public void isShoppingCartEmpty() {

        assertThat(homepage.getNumberOfProductsAtShoppingCart(), is(equalTo(0)));
    }


    @Test
    public void validadeProductInfo_NameAndValue() {

        // readable version
        String productName_home = homepage.getTShirtNameHome(productIndex).toLowerCase();
        String productPrice_home = homepage.getTShirtPriceHome(productIndex);

        productPage = homepage.clickProduct(productIndex);

        String productName_tshirt = productPage.getTShirtNameProductPage().toLowerCase();
        String productPrice_tshirt = productPage.getTShirtPriceProductPage();

        assertThat(productName_tshirt, is(equalTo(productName_home)));
        assertThat(productPrice_tshirt, is(equalTo(productPrice_home)));


        // compact and more optimized version (que não funciona por causa do toUpperCase() !!!!)
//        assertThat(selenium.homepage.getTShirtNameHome(index).toUpperCase(), is(equalTo(selenium.homepage.clickProduct(index).getTShirtNameProductPage().toUpperCase())));
//        assertThat(selenium.homepage.getTShirtPriceHome(index), is(equalTo(selenium.homepage.clickProduct(index).getTShirtPriceProductPage())));

    }


    @Test
    public void validateLoginAtHeader() {

        String email = "lucas@teste.com";
        String passwd = "123456";

        homepage.clickSignInButton().fillLoginForm(email, passwd);

        String homepageName = homepage.getLoginNameAtHeader();

        assertThat(homepageName, is(equalTo("Lucas Santos")));

        loadInitialPage();

    }


    @Test
    public void addProductOnShoppingCart() {

        ProductPage tShirtPage = homepage.clickProduct(productIndex);

        tShirtPage.chooseSize(productSize);
        tShirtPage.chooseColor(productColor);
        tShirtPage.chooseQuantity(productQuantity);

        modalPage = tShirtPage.addToShoppingCart();

        assertThat(modalPage.getSelectedSizeModel(), is(equalTo(productSize)));
        assertThat(modalPage.getSelectedColorModel(), is(equalTo(productColor)));
        assertThat(modalPage.getSelectedQuantityModel(), either(is(productQuantity)).or(is(productQuantityAllTests)));
        assertThat(modalPage.getSubtotalValueModel(), either(is(productPriceOne)).or(is(productPriceTotal)).or(is(productPriceAbsoluteMaximum)));
        assertThat(modalPage.getMessageProductAddedModel(), is(equalTo("Product successfully added to your shopping cart")));
    }


    @Test
    public void goToShoppingCart() {

        addProductOnShoppingCart();

        shoppingCartPage = modalPage.clickProceedToCheckout();

        assertThat(shoppingCartPage.getProductNameCart(), is(equalTo("Hummingbird printed t-shirt")));
        assertThat(shoppingCartPage.getProductPriceCart(), is(equalTo(productPriceOne)));
        assertThat(shoppingCartPage.getSubtotalPriceCart(), either(is(productPriceTotal)).or(is(productPriceAbsoluteMaximum)));
        assertThat(shoppingCartPage.getSubtotalPriceFromCheckoutCart(), either(is(productPriceTotal)).or(is(productPriceAbsoluteMaximum)));
        assertThat(shoppingCartPage.getProductSizeCart(), is(equalTo(productSize)));
        assertThat(shoppingCartPage.getProductColorCart(), is(equalTo(productColor)));
        assertThat(shoppingCartPage.getProductQuantityCart(), either(is(productQuantity)).or(is(productQuantityAllTests)));
        assertThat(shoppingCartPage.getProductQuantityFromInputCart(), either(is(productQuantity)).or(is(productQuantityAllTests)));
    }

}
