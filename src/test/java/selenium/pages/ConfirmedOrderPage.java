package selenium.pages;

import Util.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmedOrderPage {

    private WebDriver chromeDriver;


    public ConfirmedOrderPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public String getConfirmedOrderPhrase() {
        return chromeDriver.findElement(By.xpath("//section[@id='content-hook_order_confirmation']//div//h3")).getText().substring(1);
    }


    public String getEmailWhereTheOrderConfirmationWasSent() {
        return chromeDriver.findElement(By.xpath("//section[@id='content-hook_order_confirmation']//p")).getText().split(" ")[7];
    }


    public Double getUnitaryPriceConfirmationPage() {
        return Double.parseDouble(Functions.removeDollarSign(chromeDriver.findElement(By.cssSelector("div.order-confirmation-table div.text-xs-left")).getText()));
    }

    public String getPaymentTypeSelected() {
        return chromeDriver.findElement(By.xpath("//div[@id='order-details']//li[2]")).getText().split(" ")[4].toUpperCase();
    }

}
