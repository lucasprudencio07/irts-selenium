package selenium.browsers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestingChrome {

    private WebDriver chromeDriver;

    @BeforeEach
    public void setUp() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lprudencio\\Downloads\\WebDriver\\chromedriver83.exe");

        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        chromeDriver.manage().window().maximize();
        chromeDriver.get("https://www.youtube.com");
    }


    @AfterEach
    public void tearDown() throws InterruptedException {

        Thread.sleep(5000);
        chromeDriver.quit();
    }


    @Test
    public void testingJunit() {

        WebElement searchBox = chromeDriver.findElement(By.id("search-form"));

        searchBox.findElement(By.id("search")).sendKeys(Keys.chord("Hatsune Miku"));
        chromeDriver.findElement(By.id("search-icon-legacy")).click();
        chromeDriver.findElement(By.partialLinkText("World is Mine")).click();

        String numberOfViews = chromeDriver.findElement(By.cssSelector("span.view-count")).getText();

        System.out.println(numberOfViews);

//        Assertions.assertEquals(numberOfViews, "47.859.154 visualizações");
    }

}
