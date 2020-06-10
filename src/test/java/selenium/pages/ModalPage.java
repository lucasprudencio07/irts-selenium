package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import pages.ShoppingCartPage;

import java.time.Duration;
import java.util.List;

public class ModalPage {

    private WebDriver chromeDriver;

    public ModalPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public String getMessageProductAddedModel() {

        waitForThePopUpScreen();

        return chromeDriver.findElement(By.id("myModalLabel")).getText().replace("\uE876", "");
    }


    public String getSelectedSizeModel() {
        waitForThePopUpScreen();
        return chromeDriver.findElement(By.xpath(getPopUpXpath(1))).getText();
    }


    public String getSelectedColorModel() {
        waitForThePopUpScreen();
        return chromeDriver.findElement(By.xpath(getPopUpXpath(2))).getText();
    }


    public String getSelectedQuantityModel() {
        waitForThePopUpScreen();
        return chromeDriver.findElement(By.xpath(getPopUpXpath(3))).getText();
    }


    public Double getSubtotalValueModel() {
        waitForThePopUpScreen();

        // by xpath
        return Double.parseDouble(chromeDriver.findElement(By.xpath("//div[@class='cart-content']//p[2]//span[2]")).getText().replace("$", ""));

        // by cssSelector
//        return chromeDriver.findElement(By.cssSelector(".cart-content p:nth-child(2) span.value")).getText();
    }



    public ShoppingCartPage clickProceedToCheckout() {

        chromeDriver.findElement(By.cssSelector("div.cart-content-btn a.btn-primary")).click();
//        chromeDriver.findElement(By.xpath("//div[@class='cart-content-btn']//a")).click();

        return new ShoppingCartPage(chromeDriver);
    }


    public void waitForThePopUpScreen() {

        FluentWait fluentWait = new FluentWait(chromeDriver).withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModalLabel")));
    }


    private String getPopUpXpath(Integer index) {
        return "//div[@class='modal-body']//div[@class='col-md-6']//span["+ index +"]//strong";
    }


}
