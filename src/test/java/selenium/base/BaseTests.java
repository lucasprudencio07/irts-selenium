package selenium.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.pages.HomePage;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    private static WebDriver chromeDriver;
    protected HomePage homepage;


    @BeforeAll
    public static void setUp() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lprudencio\\Downloads\\WebDriver\\chromedriver83.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lucas\\Documents\\Drivers\\chromedriver83.exe");

        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        chromeDriver.manage().window().maximize();
    }

    // método que carrega a página inicial
    @BeforeEach
    public void loadInitialPage() {

        chromeDriver.get("https://marcelodebittencourt.com/demoprestashop");
        homepage = new HomePage(chromeDriver);
    }


    @AfterAll
    public static void tearDown() {

        chromeDriver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        chromeDriver.quit();
    }

}
