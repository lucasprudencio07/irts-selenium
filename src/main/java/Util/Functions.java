package Util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class Functions {

    public static String removeDollarSign(String text) {
        return text.replace("$", "");
    }

    public static void waitForThePopUpScreen(WebDriver chromeDriver) {

        FluentWait fluentWait = new FluentWait(chromeDriver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModalLabel")));
    }
}
