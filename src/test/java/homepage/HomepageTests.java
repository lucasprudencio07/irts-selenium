package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.ProductPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HomepageTests extends BaseTests {

    /*
    um disclaimer é que o "assertThat" recebe primeiro o valor atual e depois o esperado,
    já o "assertEquals" do Junit recebe primeiro o valor esperado e depois o atual.
    */

    @Test
    public void testCountProducts() {

        loadInitialPage();

        assertThat(homepage.countProducts(), is(equalTo(8)));
//        assertEquals( 8, homepage.countProducts());
    }


    @Test
    public void isShoppingCartEmpty() {

        assertThat(homepage.getNumberOfProductsAtShoppingCart(), is(equalTo(0)));
    }


    @Test
    public void validadeProductInfo_NameAndValue() {

        Integer index = 0;

        // readable version
        String productName_home = homepage.getTShirtNameHome(index).toLowerCase();
        String productPrice_home = homepage.getTShirtPriceHome(index);

        ProductPage productPage = homepage.clickProduct(index);

        String productName_tshirt = productPage.getTShirtNameProductPage().toLowerCase();
        String productPrice_tshirt = productPage.getTShirtPriceProductPage();

        assertThat(productName_tshirt, is(equalTo(productName_home)));
        assertThat(productPrice_tshirt, is(equalTo(productPrice_home)));


        // compact and more optimized version (que não funciona por causa do toUpperCase() !!!!)
//        assertThat(homepage.getTShirtNameHome(index).toUpperCase(), is(equalTo(homepage.clickProduct(index).getTShirtNameProductPage().toUpperCase())));
//        assertThat(homepage.getTShirtPriceHome(index), is(equalTo(homepage.clickProduct(index).getTShirtPriceProductPage())));

    }

}
