package selenium.browsers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class TestingOpera {

    private WebDriver operaDriver;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.opera.driver", "C:\\Users\\lprudencio\\Downloads\\WebDriver\\operadriver.exe");

        operaDriver = new OperaDriver();
        operaDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        operaDriver.manage().window().maximize();
        operaDriver.get("https://www.youtube.com");

    }

    @AfterEach
    public void tearDown() throws InterruptedException {

        Thread.sleep(5000);
        operaDriver.quit();
    }


    @Test
    public void testingJunit() {

        WebElement searchBox = operaDriver.findElement(By.id("search-form"));

        searchBox.findElement(By.id("search")).sendKeys(Keys.chord("Hatsune Miku"));
        operaDriver.findElement(By.id("search-icon-legacy")).click();
        operaDriver.findElement(By.partialLinkText("World is Mine")).click();

        String numberOfViews = operaDriver.findElement(By.cssSelector("span.view-count")).getText();

        System.out.println(numberOfViews);

        Assertions.assertEquals(numberOfViews, "47.871.297 visualizações");
    }

}
