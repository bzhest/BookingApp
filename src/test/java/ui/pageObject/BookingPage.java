package ui.pageObject;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BookingPage {

    private final String productTitle = "//div/h5[text()='%s']";
    private final String date = productTitle + "/../p[1]";
    private final String time = productTitle + "/../p[2]";
    private final String address = productTitle + "/../p[3]";
    private final String status = productTitle + "/../p[4]";
    private final String editButton = productTitle + "/..//button[text()='Edit']";
    private final String cancelButton = productTitle + "/..//button[text()='Cancel']";

    public String getProductDeliveryAddress(String productName) {
        return $(By.xpath(String.format(address, productName))).text().trim();
    }

    public void clickEdit(String productName) {
        $(By.xpath(String.format(editButton, productName))).click();
    }
}
