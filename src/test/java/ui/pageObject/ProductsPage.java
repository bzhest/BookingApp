package ui.pageObject;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductsPage {
    private final String productStatus = "//tr/td[contains(text(),'%s')]/../td[last()]";
    private final String productButton = "//tr/td[contains(text(),'%s')]/..//button";

    public String getProductStatusText(String productName) {
        return $(By.xpath(String.format(productStatus, productName))).text();
    }

    public String getProductButtonText(String productName){
        return $(By.xpath(String.format(productButton, productName))).text();
    }

    public void clickProductButton(String productName){
        $(By.xpath(String.format(productButton, productName))).click();
    }
}
