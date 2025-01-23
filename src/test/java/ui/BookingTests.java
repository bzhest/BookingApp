package ui;

import com.codeborne.selenide.Selenide;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static ui.Constants.DASHBOARD_PAGE;
import static ui.Constants.LOGIN_PAGE;

public class BookingTests extends BaseTest {

    @Test
    public void checkProductCanBeBooked() {
        open(uiConfig.getUrl() + LOGIN_PAGE);
        app.getLoginPage().login(uiConfig.getUserName(), uiConfig.getPassword());
        String product = "IpadNew3";
        open(uiConfig.getUrl() + DASHBOARD_PAGE);
        Assert.assertEquals(app.getProductPage().getProductButtonText(product), "Book");
        Assert.assertEquals(app.getProductPage().getProductStatusText(product), "Not booked");
        app.getProductPage().clickProductButton(product);
        Selenide.switchTo().alert().accept();
        Assert.assertEquals(app.getProductPage().getProductButtonText(product), "Cancel Booking");
        Assert.assertEquals(app.getProductPage().getProductStatusText(product), "Submitted");
    }

    @Test
    public void checkProductCanBeCanceled() {
        open(uiConfig.getUrl() + LOGIN_PAGE);
        app.getLoginPage().login(uiConfig.getUserName(), uiConfig.getPassword());
        String product = "IpadNew3";
        open(uiConfig.getUrl() + DASHBOARD_PAGE);
        Assert.assertEquals(app.getProductPage().getProductButtonText(product), "Book");
        Assert.assertEquals(app.getProductPage().getProductStatusText(product), "Not booked");
        app.getProductPage().clickProductButton(product);
        Selenide.switchTo().alert().accept();
        Assert.assertEquals(app.getProductPage().getProductButtonText(product), "Cancel Booking");
        Assert.assertEquals(app.getProductPage().getProductStatusText(product), "Submitted");
        app.getProductPage().clickProductButton(product);
        Selenide.switchTo().alert().accept();
        Assert.assertEquals(app.getProductPage().getProductButtonText(product), "Book");
        Assert.assertEquals(app.getProductPage().getProductStatusText(product), "Canceled");
        app.getProductPage().clickProductButton(product);
        Selenide.switchTo().alert().accept();
    }
}
