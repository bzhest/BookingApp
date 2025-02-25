package ui;

import com.bookStore.tests.UiConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import ui.pageObject.App;


public class BaseTests {
    protected final App app = new App();
    protected UiConfig uiConfig = ConfigFactory.create(UiConfig.class);
    protected Faker faker = new Faker();

    @BeforeAll
    public static void before(){
        Configuration.remote = "http://chrome/wd/hub";
        Configuration.browser = "chrome";
    }
}
