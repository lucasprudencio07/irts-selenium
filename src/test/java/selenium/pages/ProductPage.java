package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    private WebDriver chromeDriver;

    public ProductPage(WebDriver chromeDriver) {

        this.chromeDriver = chromeDriver;

    }

    public String getTShirtNameProductPage() {
        return chromeDriver.findElement(By.className("h1")).getText();
    }


    public String getTShirtPriceProductPage() {
        return chromeDriver.findElement(By.cssSelector(".current-price span")).getText();
    }

}
