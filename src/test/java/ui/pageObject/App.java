package ui.pageObject;

public class App {

    private ProductsPage productsPage;
    private LoginPage loginPage;

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
