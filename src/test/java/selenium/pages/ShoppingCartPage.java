package selenium.pages;

import Util.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pages.CheckoutPage;

public class ShoppingCartPage {

    private WebDriver chromeDriver;


    public ShoppingCartPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public String getProductNameCart() {
        return chromeDriver.findElement(By.xpath("//div[@class='product-line-info']//a")).getText();
    }


    public Double getProductPriceCart() {
        return Double.parseDouble(Functions.removeDollarSign(chromeDriver.findElement(By.cssSelector("span.price")).getText()));
    }


    public String getProductSizeCart() {
        return chromeDriver.findElement(By.xpath("//div[@class='product-line-info'][2]//span[@class='value']")).getText();
    }


    public String getProductColorCart() {
        return chromeDriver.findElement(By.xpath("//div[@class='product-line-info'][3]//span[@class='value']")).getText();
    }


    public String getProductQuantityCart() {
        return chromeDriver.findElement(By.cssSelector("span.js-subtotal")).getText().split(" ")[0];
    }

    public String getProductQuantityFromInputCart() {
        return chromeDriver.findElement(By.cssSelector("div.bootstrap-touchspin input.js-cart-line-product-quantity")).getAttribute("value");
    }


    public Double getSubtotalPriceCart() {
        return Double.parseDouble(Functions.removeDollarSign(chromeDriver.findElement(By.xpath("//div//span[@class='product-price']")).getText()));
    }


    public Double getSubtotalPriceFromCheckoutCart() {
        return Double.parseDouble(chromeDriver.findElement(By.xpath("//div[@id='cart-subtotal-products']//span[2]")).getText().replace("$", ""));
    }


    public CheckoutPage clickProceedToCheckout(){

        chromeDriver.findElement(By.cssSelector("a.btn-primary")).click();

        return new CheckoutPage(chromeDriver);
    }
}
