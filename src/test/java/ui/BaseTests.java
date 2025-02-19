package ui;

import com.bookStore.tests.UiConfig;
import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import ui.pageObject.App;

public class BaseTests {
    protected final App app = new App();
    protected UiConfig uiConfig = ConfigFactory.create(UiConfig.class);
    protected Faker faker = new Faker();
}
