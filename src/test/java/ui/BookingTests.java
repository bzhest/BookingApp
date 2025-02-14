package ui;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Address;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static ui.Constants.*;

public class BookingTests extends BaseTest {

    @Test
    public void checkProductCanBeBooked() {
        open(uiConfig.getUrl() + LOGIN_PAGE);
        app.getLoginPage().login(uiConfig.getManagerUsername(), uiConfig.getManagerPassword());
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
        app.getLoginPage().login(uiConfig.getManagerUsername(), uiConfig.getManagerPassword());
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

    @Test
    public void checkCustomerCanEditBooking() {
        open(uiConfig.getUrl() + LOGIN_PAGE);
        app.getLoginPage().login(uiConfig.getCustomerUsername(), uiConfig.getCustomerPassword());
        String product = "iPad11";
        String newAddress = faker.address().streetAddress();
        open(uiConfig.getUrl() + BOOKINGS_PAGE);
        app.getBookingPage().clickEdit(product);
        app.getEditBookingModal().fillAddressField(newAddress);
        app.getEditBookingModal().clickSaveChangesButton();
        Selenide.refresh();
        Assertions.assertTrue(app.getBookingPage().getProductDeliveryAddress(product).contains(newAddress));
    }
}
