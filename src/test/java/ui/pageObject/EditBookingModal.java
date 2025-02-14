package ui.pageObject;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class EditBookingModal {
    private final By addressField = By.id("address");
    private final By saveChangesButton = By.id("saveChanges");

    public void fillAddressField(String text) {
        $(addressField).setValue(text);
    }

    public void clickSaveChangesButton(){
        $(saveChangesButton).click();
    }

}
