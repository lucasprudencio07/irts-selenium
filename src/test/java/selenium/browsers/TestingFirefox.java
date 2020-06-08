package selenium.browsers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class TestingFirefox {

    private WebDriver firefoxDriver;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\lprudencio\\Downloads\\WebDriver\\geckodriver.exe");

        firefoxDriver = new FirefoxDriver();

        firefoxDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        firefoxDriver.manage().window().fullscreen();

        firefoxDriver.get("https://www.youtube.com");

    }


    @AfterEach
    public void tearDown() {

        firefoxDriver.close();
    }


    @Test
    public void testingJunit() throws InterruptedException {

        WebElement searchBox = firefoxDriver.findElement(By.id("search-form"));

        searchBox.findElement(By.id("search")).sendKeys("Hatsune Miku");
        firefoxDriver.findElement(By.id("search-icon-legacy")).click();
        firefoxDriver.findElement(By.partialLinkText("World is Mine")).click();

        WebElement controlBar = firefoxDriver.findElement(By.className("ytp-chrome-controls"));

        WebElement settingsButton = controlBar.findElement(By.className("ytp-settings-button"));
        settingsButton.click();
    }

}


