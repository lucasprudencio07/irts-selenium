package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver chromeDriver;
    private List<WebElement> productsListAtHomepage = new ArrayList<WebElement>();


    public HomePage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        loadProductsList();
    }

    // click que leva ao ProductPage
    public ProductPage clickProduct(Integer index) {

        productsListAtHomepage.get(index).findElement(By.cssSelector(".product-description a")).click();

        return new ProductPage(chromeDriver);
    }

    // click que leva a página de login
    public LoginPage clickSignInButton() {

        chromeDriver.findElement(By.className("user-info")).click();

        return new LoginPage(chromeDriver);
    }


    public Integer countProducts() {

        return productsListAtHomepage.size();
    }


    public Integer getNumberOfProductsAtShoppingCart() {

        String stringQuantity = chromeDriver.findElement(By.className("cart-products-count")).getText();

        return Integer.parseInt(stringQuantity.substring(1, 2));
    }


    public String getTShirtNameHome(Integer index) {

        return productsListAtHomepage.get(index).findElement(By.cssSelector(".product-description a")).getText();
    }


    public String getTShirtPriceHome(Integer index) {

        WebElement productPriceBar = productsListAtHomepage.get(index).findElement(By.className("product-price-and-shipping"));
        return productPriceBar.findElement(By.className("price")).getText();
    }


    private void loadProductsList() {
        productsListAtHomepage = chromeDriver.findElements(By.className("product-description"));
    }

    public String getLoginNameAtHeader() {
        return chromeDriver.findElement(By.className("account")).findElement(By.className("hidden-sm-down")).getText();
    }

    public String getTextAtHeader() {
        return chromeDriver.findElement(By.cssSelector("div.user-info span.hidden-sm-down:nth-child(2)")).getText();
    }

}
