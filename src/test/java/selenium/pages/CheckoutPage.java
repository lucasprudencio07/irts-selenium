package selenium.pages;

import Util.Functions;
import io.cucumber.core.internal.gherkin.Func;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private final WebDriver chromeDriver;

    public CheckoutPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public Double getSubtotalPriceCheckout() {
        return Double.parseDouble(Functions.removeDollarSign(chromeDriver.findElement(By.xpath("//div[@id='cart-subtotal-products']//span[2]")).getText()));
    }


    public String getNameInsideAddress() {
            return chromeDriver.findElement(By.xpath("//div[@class='address']")).getText().split("\n")[0];
    }

    public void clickContinueToSelectShippingMethod() {
        chromeDriver.findElement(By.xpath("//div[@class='clearfix']//button[@value='1']")).click();
    }


    public Double getShippingMethodPrice() {
        return Double.parseDouble(Functions.removeDollarSign(chromeDriver.findElement(By.xpath("//span[@class='carrier-price']")).getText().split(" ")[0]));
    }


    public void clickContinueToPayment() {
        chromeDriver.findElement(By.xpath("//form[@id='js-delivery']//button")).click();
    }


    public void getSelectedPayment() {
        chromeDriver.findElement(By.id("payment-option-1")).click();
    }


    public Double getPayByCheckValue() {
        return Double.parseDouble(Functions.removeDollarSign(chromeDriver.findElement(By.xpath("//div[@id='payment-option-1-additional-information']//dd[1]")).getText().split(" ")[0]));
    }

    public Double getTotalWithTax() {
        return Double.parseDouble(Functions.removeDollarSign(chromeDriver.findElement(By.xpath("//div[@class='cart-summary-line'][1]//span[2]\n")).getText()));
    }


    public void clickToAgreeTermsOfService() {
        chromeDriver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
    }

    public boolean isAgreeTermsOfServiceSelected() {
        return chromeDriver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).isSelected();
    }

    public ConfirmedOrderPage clickOrderWithAnObligationToPay() {

        chromeDriver.findElement(By.xpath("//div[@id='payment-confirmation']//button")).click();

        return new ConfirmedOrderPage(chromeDriver);
    }

}
