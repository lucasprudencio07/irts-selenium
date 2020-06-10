package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductPage {

    private WebDriver chromeDriver;


    public ProductPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public String getTShirtNameProductPage() {
        return chromeDriver.findElement(By.className("h1")).getText();
    }


    public String getTShirtPriceProductPage() {
        return chromeDriver.findElement(By.cssSelector(".current-price span")).getText();
    }


    public void chooseSize(String size) {

//        Select selectOption = new Select(chromeDriver.findElement(By.id("group_1")));

//        List<WebElement> selectList = selectOption.getOptions();
//
//        List<String> selectListString = new ArrayList<>();
//
//        for (WebElement element : selectList) {
//            selectListString.add(element.getText());
//        }
//
//        System.out.println(selectListString);

        new Select(chromeDriver.findElement(By.id("group_1"))).selectByVisibleText(size);

    }


    public void chooseColor(String color) {

        if (color.toLowerCase().equals("white")) {
            chromeDriver.findElement(By.xpath("//ul[@id='group_2']//input[@value='8']")).click();
        }
        else if (color.toLowerCase().equals("black")){
            chromeDriver.findElement(By.xpath("//ul[@id='group_2']//input[@value='11']")).click();
        }

    }


    public void chooseQuantity(String quantity) {

        // este método limpa o input de quantidade e adiciona a variável
        chromeDriver.findElement(By.id("quantity_wanted")).clear();
        chromeDriver.findElement(By.id("quantity_wanted")).sendKeys(quantity);

        // já este irá apenas apertar o botão de aumentar 1 na quantidade
//        chromeDriver.findElement(By.className("bootstrap-touchspin-up")).click();
    }


    public ModalPage addToShoppingCart() {

        chromeDriver.findElement(By.className("add-to-cart")).click();

        return new ModalPage(chromeDriver);
    }

}
