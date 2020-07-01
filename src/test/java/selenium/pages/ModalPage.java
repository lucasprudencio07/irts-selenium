package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static Util.Functions.waitForThePopUpScreen;

public class ModalPage {

    private WebDriver chromeDriver;

    public ModalPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public String getMessageProductAddedModel() {
        callFluentWait();
        return chromeDriver.findElement(By.id("myModalLabel")).getText().replace("\uE876", "");
    }

    public String getSelectedProductNameModel() {
        callFluentWait();
        return chromeDriver.findElement(By.cssSelector("div.modal-body h6")).getText();
    }

    public String getSelectedSizeModel() {
        callFluentWait();
        return chromeDriver.findElement(By.xpath(getPopUpXpath(1))).getText();
    }


    public String getSelectedColorModel() {
        callFluentWait();

        String color = chromeDriver.findElement(By.xpath(getPopUpXpath(2))).getText();

        if (color.contains("Color"))
            return color;

        return "N/A";
    }


    public String getSelectedQuantityModel() {
        callFluentWait();
        try {
            return chromeDriver.findElement(By.xpath(getPopUpXpath(3))).getText();
        } catch (NoSuchElementException ne) {
            return chromeDriver.findElement(By.xpath(getPopUpXpath(2))).getText();
        }
    }


    public Double getSubtotalValueModel() {
        callFluentWait();

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


    private String getPopUpXpath(Integer index) {
        return "//div[@class='modal-body']//div[@class='col-md-6']//span["+ index +"]//strong";
    }


    private void callFluentWait() {
        waitForThePopUpScreen(chromeDriver);
    }

}
