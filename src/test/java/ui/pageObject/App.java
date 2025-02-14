package ui.pageObject;

import com.bookStore.entity.Booking;

public class App {

    private ProductsPage productsPage;
    private LoginPage loginPage;
    private BookingPage bookingPage;
    private EditBookingModal editBookingModal;

    public BookingPage getBookingPage() {
        if (null == bookingPage) {
            bookingPage = new BookingPage();
        }
        return bookingPage;
    }

    public EditBookingModal getEditBookingModal() {
        if (null == editBookingModal) {
            editBookingModal = new EditBookingModal();
        }
        return editBookingModal;
    }

    public ProductsPage getProductPage() {
        if (null == productsPage) {
            productsPage = new ProductsPage();
        }
        return productsPage;
    }

    public LoginPage getLoginPage() {
        if (null == loginPage) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }
}
