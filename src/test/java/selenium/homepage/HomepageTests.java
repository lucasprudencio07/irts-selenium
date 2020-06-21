package selenium.homepage;

import org.junit.jupiter.api.Test;
import pages.ShoppingCartPage;
import selenium.base.BaseTests;
import selenium.pages.CheckoutPage;
import selenium.pages.ConfirmedOrderPage;
import selenium.pages.ModalPage;
import selenium.pages.ProductPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomepageTests extends BaseTests {


    private final String email = "lucas@teste.com";
    private final String passwd = "123456";
    private final String confirmedOrderPhrase = "Your order is confirmed";
    private final String myName = "Lucas Santos";
    private final String productSize = "M";
    private final String productColor = "Black";
    private final String productQuantity = "2";
    private final String productQuantityTimes2 = "4";
    private final String productQuantityTimes3 = "6";
    private final String productQuantityTimes4 = "8";
    private final Integer productIndex = 0;
    private final Double productPrice1 = 19.12;
    private final Double productPrice2 = 38.24;
    private final Double productPrice4 = 76.48;
    private final Double productPrice6 = productPrice2 + productPrice4;
    private final Double productPrice8 = productPrice4 * 2;
    private final Double shippingPrice = 7.00;
    private final String paymentType = "CHECK";

    private ModalPage modalPage;
    private ProductPage productPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutPage checkoutPage;
    private ConfirmedOrderPage confirmedOrderPage;

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
    public void testIsShoppingCartEmpty() {

        assertThat(homepage.getNumberOfProductsAtShoppingCart(),  either(is(0)).or(is(1)).or(is(2)).or(is(4)).or(is(6)).or(is(8)));
    }


    @Test
    public void testValidateProductInfo_NameAndValue() {

        // readable version
        String productName_home = homepage.getTShirtNameHome(productIndex).toLowerCase();
        String productPrice_home = homepage.getTShirtPriceHome(productIndex);

        productPage = homepage.clickProduct(productIndex);

        String productName_tshirt = productPage.getTShirtNameProductPage().toLowerCase();
        String productPrice_tshirt = productPage.getTShirtPriceProductPage();

        assertThat(productName_tshirt, is(equalTo(productName_home)));
        assertThat(productPrice_tshirt, is(equalTo(productPrice_home)));

    }

    @Test
    public void testValidateLoginAtHeader() {

        if (!homepage.getTextAtHeaderValidation().equals(myName)) {

            homepage.clickSignInButton().fillLoginForm(email, passwd);

            String homepageName = homepage.getLoginNameAtHeader();

            assertThat(homepageName, is(equalTo(myName)));

            loadInitialPage();
        }

    }


    @Test
    public void testAddProductOnShoppingCart() {

        ProductPage tShirtPage = homepage.clickProduct(productIndex);

        tShirtPage.chooseSize(productSize);
        tShirtPage.chooseColor(productColor);
        tShirtPage.chooseQuantity(productQuantity);

        modalPage = tShirtPage.addToShoppingCart();

        assertThat(modalPage.getSelectedSizeModel(), is(equalTo(productSize)));
        assertThat(modalPage.getSelectedColorModel(), is(equalTo(productColor)));
        assertThat(modalPage.getSelectedQuantityModel(), either(is(productQuantity)).or(is(productQuantityTimes2)).or(is(productQuantityTimes3)).or(is(productQuantityTimes4)));
        assertThat(modalPage.getSubtotalValueModel(), either(is(productPrice1)).or(is(productPrice2)).or(is(productPrice4)).or(is(productPrice6)).or(is(productPrice8)));
        assertThat(modalPage.getMessageProductAddedModel(), is(equalTo("Product successfully added to your shopping cart")));
    }


    @Test
    public void testGoToShoppingCart() {

        testAddProductOnShoppingCart();

        shoppingCartPage = modalPage.clickProceedToCheckout();

        assertThat(shoppingCartPage.getProductNameCart(), is(equalTo("Hummingbird printed t-shirt")));
        assertThat(shoppingCartPage.getProductPriceCart(), is(equalTo(productPrice1)));
        assertThat(shoppingCartPage.getSubtotalPriceCart(), either(is(productPrice2)).or(is(productPrice4)).or(is(productPrice6)).or(is(productPrice8)));
        assertThat(shoppingCartPage.getSubtotalPriceFromCheckoutCart(), either(is(productPrice2)).or(is(productPrice4)).or(is(productPrice6)).or(is(productPrice8)));
        assertThat(shoppingCartPage.getProductSizeCart(), is(equalTo(productSize)));
        assertThat(shoppingCartPage.getProductColorCart(), is(equalTo(productColor)));
        assertThat(shoppingCartPage.getProductQuantityCart(), either(is(productQuantity)).or(is(productQuantityTimes2)).or(is(productQuantityTimes3)).or(is(productQuantityTimes4)));
        assertThat(shoppingCartPage.getProductQuantityFromInputCart(), either(is(productQuantity)).or(is(productQuantityTimes2)).or(is(productQuantityTimes3)).or(is(productQuantityTimes4)));
    }


    @Test
    public void testValidateAddressAndPaymentMethod() {

        // a necessary condition, because this method need to have something inside the cart
        if (!homepage.getTextAtHeaderValidation().equals(myName)) {
            testValidateLoginAtHeader();
        }
        testGoToShoppingCart();

        checkoutPage = shoppingCartPage.clickProceedToCheckout();

        assertThat(checkoutPage.getSubtotalPriceCheckout(), either(is(productPrice2)).or(is(productPrice4)).or(is(productPrice6)).or(is(productPrice8)));
        assertThat(checkoutPage.getNameInsideAddress(), is(equalTo(myName)));

        checkoutPage.clickContinueToSelectShippingMethod();

        assertThat(checkoutPage.getShippingMethodPrice(), is(equalTo(shippingPrice)));

        checkoutPage.clickContinueToPayment();
        checkoutPage.getSelectedPayment();

        assertThat(checkoutPage.getPayByCheckValue(), is(equalTo(checkoutPage.getTotalWithTax())));

        checkoutPage.clickToAgreeTermsOfService();

        assertTrue(checkoutPage.isAgreeTermsOfServiceSelected());
    }


    @Test
    public void testValidateOrderConfirmationData() {

        testValidateAddressAndPaymentMethod();

        confirmedOrderPage = checkoutPage.clickOrderWithAnObligationToPay();

        assertThat(confirmedOrderPage.getConfirmedOrderPhrase().toUpperCase(), is(equalTo(confirmedOrderPhrase.toUpperCase())));
        assertThat(confirmedOrderPage.getEmailWhereTheOrderConfirmationWasSent(), is(equalTo(email)));
        assertThat(confirmedOrderPage.getUnitaryPriceConfirmationPage(), is(equalTo(productPrice1)));
        assertThat(confirmedOrderPage.getPaymentTypeSelected(), is(equalTo(paymentType)));

    }

}
