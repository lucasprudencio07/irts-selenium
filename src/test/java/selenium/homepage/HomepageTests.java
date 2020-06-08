package selenium.homepage;

import selenium.base.BaseTests;
import org.junit.jupiter.api.Test;
import selenium.pages.ModalPage;
import selenium.pages.ProductPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HomepageTests extends BaseTests {



    private final String productSize = "M";
    private final String productColor = "Black";
    private final String productQuantity = "2";
    private final Integer productIndex = 0;


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

        ProductPage productPage = homepage.clickProduct(productIndex);

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

        validateLoginAtHeader();

        if (homepage.getTextAtHeader().equals("Lucas Santos")) {
           validateLoginAtHeader();
        }

        ProductPage tShirtPage = homepage.clickProduct(productIndex);

        tShirtPage.selectSize(productSize);
        tShirtPage.selectColor(productColor);
        tShirtPage.selectQuantity(productQuantity);

        ModalPage modalPage = tShirtPage.addToShoppingCart();

        assertThat(modalPage.getSelectedSizeModel(), is(equalTo(productSize)));
        assertThat(modalPage.getSelectedColorModel(), is(equalTo(productColor)));
        assertThat(modalPage.getSelectedQuantityModel(), is(equalTo(productQuantity)));
        assertThat(modalPage.getSubtotalValueModel(), is(equalTo(38.24)));
        assertThat(modalPage.getMessageProductAddedModel(), is(equalTo("Product successfully added to your shopping cart")));
    }


}
