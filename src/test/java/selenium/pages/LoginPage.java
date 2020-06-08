package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver chromeDriver;


    public LoginPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public void fillLoginForm(String email, String password) {

        chromeDriver.findElement(By.id("login-form")).findElement(By.name("email")).sendKeys(email);
        chromeDriver.findElement(By.id("login-form")).findElement(By.name("password")).sendKeys(password);

        clickSignInCredencials();
    }


    private void clickSignInCredencials() {

        chromeDriver.findElement(By.id("submit-login")).click();
    }

}
