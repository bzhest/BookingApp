package ui;

import com.bookStore.tests.UiConfig;
import org.aeonbits.owner.ConfigFactory;
import ui.pageObject.App;

public class BaseTest {
    protected final App app = new App();
    protected UiConfig uiConfig = ConfigFactory.create(UiConfig.class);
}
