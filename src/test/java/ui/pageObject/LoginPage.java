package ui.pageObject;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final By name = By.id("username");
    private final By password = By.id("password");
    private final By signIn = By.xpath("//button[@type='submit']");

    public void login(String userName, String pass){
        $(name).setValue(userName);
        $(password).setValue(pass);
        $(signIn).click();
    }
}
